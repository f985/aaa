package am.rockstars.mapper;

import am.rockstars.entity.Product;
import am.rockstars.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ProductMapper {

    ProductResponse mapToProductResponse(Product product);
}
