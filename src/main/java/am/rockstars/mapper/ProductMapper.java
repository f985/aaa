package am.rockstars.mapper;

import am.rockstars.dto.CreateProductRequest;
import am.rockstars.dto.ProductBean;
import am.rockstars.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "createdById", target = "createdBy.id")
    Product map(CreateProductRequest request);

    @Mapping(source = "createdBy.id", target = "createdById")
    ProductBean map(Product product);
}
