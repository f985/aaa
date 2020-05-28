package am.rockstars.repository;

import am.rockstars.entity.Product;
import am.rockstars.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    void deleteAllByProduct(Product product);
}
