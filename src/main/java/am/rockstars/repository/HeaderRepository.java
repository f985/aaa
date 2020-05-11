package am.rockstars.repository;

import am.rockstars.entity.Header;
import am.rockstars.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeaderRepository extends JpaRepository<Header, Long> {

}
