package am.rockstars.repository;

import am.rockstars.entity.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<BlogTag, Long> {

    Optional<BlogTag> findByName(final String names);
}
