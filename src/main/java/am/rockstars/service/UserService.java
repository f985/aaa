package am.rockstars.service;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.EditUserProfileRequest;
import am.rockstars.dto.UserResponse;
import am.rockstars.entity.User;
import am.rockstars.enums.UserRole;
import am.rockstars.mapper.UserMapper;
import am.rockstars.repository.UserRepository;
import am.rockstars.security.config.BCryptConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public User createUser(CreateUserRequest createUserRequest) {
        User userEntity = userMapper.map(createUserRequest);
        userEntity.setPassword(
                bCryptConfiguration.passwordEncoder().encode(userEntity.getPassword()));
        userEntity.setRole(UserRole.CUSTOMER);
        return userRepository.save(userEntity);
    }

    public UserResponse editUser(String email, EditUserProfileRequest editUserProfileRequest) {
        log.info("Trying edit user {} with {}", email, editUserProfileRequest);
        User user = userRepository.findByEmail(email);
        User userEntity = userMapper.map(editUserProfileRequest);
        userEntity.setId(user.getId());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setOrders(user.getOrders());
        final User savedUser = userRepository.save(userEntity);
        log.info("Successfully edited user {} with {}", email, editUserProfileRequest);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email);
        return userMapper.map(userEntity);
    }

    public UserResponse getUserByEmail(String email) {
        User userEntity = userRepository.findByEmail(email);
        return userMapper.mapToUserResponse(userEntity);
    }
}
