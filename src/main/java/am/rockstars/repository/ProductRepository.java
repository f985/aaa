package am.rockstars.repository;

import am.rockstars.entity.Product;
import am.rockstars.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByNameAndType(final String name, final ProductType type);
}
