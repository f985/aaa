package am.rockstars.repository;

import am.rockstars.entity.Feature;
import am.rockstars.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    void deleteAllByProduct(Product product);
}
