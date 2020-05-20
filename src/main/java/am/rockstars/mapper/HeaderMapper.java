package am.rockstars.mapper;

import am.rockstars.dto.header.*;
import am.rockstars.entity.HeaderChild;
import am.rockstars.entity.HeaderChildElement;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface HeaderMapper {
    List<Header> map(List<am.rockstars.entity.Header> headers);

    List<HeaderEdit> mapEditResponse(List<am.rockstars.entity.Header> headers);

    am.rockstars.entity.Header map(CreateHeaderRequest request);

    HeaderChild map(CreateHeaderChildRequest request);

    HeaderChildElement map(CreateHeaderChildElementRequest request);
}
