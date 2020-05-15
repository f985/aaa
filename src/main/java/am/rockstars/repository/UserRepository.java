package am.rockstars.repository;

import am.rockstars.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByResetKey(String key);

    Optional<User> findByActivationKey(String key);

    User findOneByEmail(String email);
}
