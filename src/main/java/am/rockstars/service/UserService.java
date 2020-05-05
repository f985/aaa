package am.rockstars.service;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.UserBean;
import am.rockstars.entity.User;
import am.rockstars.mapper.UserMapper;
import am.rockstars.repository.UserRepository;
import am.rockstars.security.config.BCryptConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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

        return userRepository.save(userEntity);
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
