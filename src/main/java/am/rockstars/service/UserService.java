package am.rockstars.service;

import am.rockstars.dto.user.CreateUserRequest;
import am.rockstars.dto.user.EditUserProfileRequest;
import am.rockstars.dto.user.UserResponse;
import am.rockstars.entity.User;
import am.rockstars.enums.UserRole;
import am.rockstars.exception.InvalidPasswordException;
import am.rockstars.mapper.UserMapper;
import am.rockstars.repository.UserRepository;
import am.rockstars.security.config.BCryptConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
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

    @Transactional
    public User createUser(final CreateUserRequest createUserRequest) {
        log.debug("Trying Create user with email: {}", createUserRequest.getEmail());
        User userEntity = userMapper.map(createUserRequest);
        userEntity.setPassword(
                bCryptConfiguration.passwordEncoder().encode(userEntity.getPassword()));
        userEntity.setRole(UserRole.CUSTOMER);
        //add activation mail sender service
        userEntity.setActivationKey(UUID.randomUUID().toString());
        return userRepository.save(userEntity);
    }

    @Transactional
    public UserResponse editUserProfile(final String email, final EditUserProfileRequest editUserProfileRequest) {
        log.debug("Trying edit user {} with {}", email, editUserProfileRequest);
        final User user = userRepository.findOneByEmail(email);
        BeanUtils.copyProperties(editUserProfileRequest, user);
        final User savedUser = userRepository.save(user);
        log.debug("Successfully edited user {} with {}", email, editUserProfileRequest);
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

    public User getById(final long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public UserResponse getUserResponseByEmail(final String email) {
        final User userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return userMapper.mapToUserResponse(userEntity);
    }

    public void activateRegistration(final String key) {
        log.debug("Activating user for activation key {}", key);
        userRepository.findByActivationKey(key)
                .ifPresentOrElse(this::activateUser,
                        () -> log.warn("Cannot find user with activation key: {}", key));
    }

    public void completePasswordReset(final String newPassword, final String key) {
        log.debug("Reset user password for reset key {}", key);
        userRepository.findByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .ifPresentOrElse(user -> resetUserPasswordByMailKey(user, newPassword),
                        () -> log.warn("Cannot find user with Reset key {}", key));
    }

    public void requestPasswordReset(final String email) {
        log.debug("Trying reset password for User with email: {}", email);
        userRepository.findByEmail(email)
                .filter(User::isActivated)
                .ifPresentOrElse(this::createUserPasswordResetMail,
                        () -> log.warn("Cannot find user with email {}", email));
    }

    public void changePassword(final String email, final String currentClearTextPassword, final String newPassword) {
        log.debug("Trying change password for User with email: {}", email);
        final User user = userRepository.findOneByEmail(email);
        if (!bCryptConfiguration.passwordEncoder().matches(currentClearTextPassword, user.getPassword())) {
            throw new InvalidPasswordException();
        }
        user.setPassword(bCryptConfiguration.passwordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    private void createUserPasswordResetMail(final User user) {
        user.setResetKey(UUID.randomUUID().toString());
        user.setResetDate(Instant.now());
        userRepository.save(user);
//        (mailService).sendPasswordResetMail(user.getEmail);
        log.debug("Successfully created password reset mail with for User with email: {}", user);
    }

    private void activateUser(User user) {
        user.setActivated(true);
        user.setActivationKey(null);
        userRepository.save(user);
        log.debug("Activated user with email: {}", user.getEmail());
    }

    private void resetUserPasswordByMailKey(final User user, final String newPassword) {
        user.setPassword(bCryptConfiguration.passwordEncoder().encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        userRepository.save(user);
        log.debug("Successfully changed password using reset mail key for user with email: {}", user.getEmail());
    }
}
