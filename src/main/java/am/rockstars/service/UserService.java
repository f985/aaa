package am.rockstars.service;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.EditUserProfileRequest;
import am.rockstars.dto.UserResponse;
import am.rockstars.entity.User;
import am.rockstars.enums.UserRole;
import am.rockstars.exception.EmailAlreadyUsedException;
import am.rockstars.exception.InvalidPasswordException;
import am.rockstars.exception.WrongActivationKeyException;
import am.rockstars.exception.WrongResetKeyException;
import am.rockstars.mapper.UserMapper;
import am.rockstars.repository.UserRepository;
import am.rockstars.security.config.BCryptConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptConfiguration bCryptConfiguration;


    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptConfiguration bCryptConfiguration) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptConfiguration = bCryptConfiguration;
    }

    public User createUser(final CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }
        User userEntity = userMapper.map(createUserRequest);
        userEntity.setPassword(
                bCryptConfiguration.passwordEncoder().encode(userEntity.getPassword()));
        userEntity.setRole(UserRole.CUSTOMER);
        userEntity.setActivationKey(UUID.randomUUID().toString());
        return userRepository.save(userEntity);
    }

    public UserResponse editUserProfile(final String email, final EditUserProfileRequest editUserProfileRequest) {
        log.info("Trying edit user {} with {}", email, editUserProfileRequest);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        BeanUtils.copyProperties(editUserProfileRequest, user);
        final User savedUser = userRepository.save(user);
        log.info("Successfully edited user {} with {}", email, editUserProfileRequest);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return userMapper.map(userEntity);
    }

    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public UserResponse getUserResponseByEmail(final String email) {
        final User userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return userMapper.mapToUserResponse(userEntity);
    }

    public User activateRegistration(final String key) {
        log.debug("Activating user for activation key {}", key);
        final Optional<User> userOptional = userRepository.findByActivationKey(key);
        if (userOptional.isEmpty()) {
            throw new WrongActivationKeyException(key);
        }
        return userOptional
                .map(user -> {
                    user.setActivated(true);
                    user.setActivationKey(null);
                    userRepository.save(user);
                    log.debug("Activated user: {}", user);
                    return user;
                }).get();
    }

    public void completePasswordReset(final String newPassword, final String key) {
        log.debug("Reset user password for reset key {}", key);
        final Optional<User> optionalUser = userRepository.findByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setPassword(bCryptConfiguration.passwordEncoder().encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    userRepository.save(user);
                    return user;
                });
        if (optionalUser.isEmpty()) {
            throw new WrongResetKeyException(key);
        }

    }

    public String requestPasswordReset(final String email) {
        log.debug("Trying reset password for User with email: {}", email);
        final Optional<User> userOptional = userRepository.findByEmail(email)
                .filter(User::isActivated)
                .map(user -> {
                    user.setResetKey(UUID.randomUUID().toString());
                    user.setResetDate(Instant.now());
                    userRepository.save(user);
                    //(mailService or smsService).sendPasswordResetMail(user.getEmail);
                    log.debug("Successfully created reset key and date for User with email: {}", user);
                    return user;
                });
        if (userOptional.isEmpty()) {
            log.warn("Password reset requested for non existing email '{}'", email);
        } else { //this part will remove after implementing mail or sms service

            return userOptional.get().getResetKey();
        }
        return "wrong email";
    }

    public void changePassword(final String email, final String currentClearTextPassword, final String newPassword) {
        log.debug("Trying change password for User with email: {}", email);
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!bCryptConfiguration.passwordEncoder().matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    user.setPassword(bCryptConfiguration.passwordEncoder().encode(newPassword));
                    userRepository.save(user);
                    log.debug("Changed password for User: {}", user);
                });
    }

    private boolean removeNonActivatedUser(final User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

}
