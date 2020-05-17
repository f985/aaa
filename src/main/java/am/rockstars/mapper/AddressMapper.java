package am.rockstars.mapper;

import am.rockstars.dto.AddressPayload;
import am.rockstars.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface AddressMapper {

    @Mappings(
            @Mapping(target = "userId", source = "user.id")
    )
    AddressPayload map(Address address);

    Address map(AddressPayload user);

}
