package am.rockstars.mapper;

import am.rockstars.dto.AboutInformationPayload;
import am.rockstars.entity.AboutInformation;
import am.rockstars.response.AboutInformationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface AboutInformationMapper {

    AboutInformationResponse mapToResponse(AboutInformation aboutInformation);

    AboutInformation map(AboutInformationPayload payload);
}
