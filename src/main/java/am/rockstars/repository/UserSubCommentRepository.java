package am.rockstars.repository;

import am.rockstars.entity.UserSubComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubCommentRepository extends JpaRepository<UserSubComment, Long> {
}
