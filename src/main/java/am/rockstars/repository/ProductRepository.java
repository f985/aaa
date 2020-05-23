package am.rockstars.repository;

import am.rockstars.entity.Product;
import am.rockstars.entity.QProduct;
import am.rockstars.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {

    Optional<Product> findByNameAndType(final String name, final ProductType type);
}
