package am.rockstars.mapper;

import am.rockstars.dto.FeatureInfo;
import am.rockstars.dto.GetProductDetailedInfoResponse;
import am.rockstars.dto.ProductPayload;
import am.rockstars.dto.TagInfo;
import am.rockstars.entity.Feature;
import am.rockstars.entity.Product;
import am.rockstars.entity.Tag;
import am.rockstars.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "createdBy.id", target = "createdById")
    GetProductDetailedInfoResponse mapToProductDetailedInfoResponse(Product product);

    ProductResponse mapToProductResponse(Product product);

    Product mapToProduct(ProductPayload payload);

    FeatureInfo map(Feature feature);

    Feature map(FeatureInfo featureInfo);

    TagInfo map(Tag tag);

    Tag map(TagInfo tagInfo);
}
