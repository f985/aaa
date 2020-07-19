package am.rockstars.repository;

import am.rockstars.entity.BlogAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogAuthorRepository extends JpaRepository<BlogAuthor, Long> {

    Optional<BlogAuthor> findByName(final String name);
}
