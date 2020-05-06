package am.rockstars.service;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.UserBean;
import am.rockstars.entity.User;
import am.rockstars.enums.UserRole;
import am.rockstars.mapper.UserMapper;
import am.rockstars.repository.UserRepository;
import am.rockstars.security.config.BCryptConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

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

    public void editUser(String email, UserBean userBean) {
        log.info("Trying edit user {} with {}", email, userBean);
        User user = userRepository.findByEmail(email);
        User userEntity = userMapper.map(userBean);
        userEntity.setId(user.getId());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setOrders(user.getOrders());
        userRepository.save(userEntity);
        log.info("Successfully edited user {} with {}", email, userBean);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email);
        return userMapper.map(userEntity);
    }

    public UserBean getUserByEmail(String email) {
        User userEntity = userRepository.findByEmail(email);
        return userMapper.mapToUserBean(userEntity);
    }
}
