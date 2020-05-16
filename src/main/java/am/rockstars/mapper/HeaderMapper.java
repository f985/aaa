package am.rockstars.mapper;

import am.rockstars.dto.header.*;
import am.rockstars.entity.Header;
import am.rockstars.entity.HeaderChild;
import am.rockstars.entity.HeaderChildElement;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface HeaderMapper {
    List<HeaderResponse> map(List<Header> headers);

    List<HeaderEditResponse> mapToEdit(List<Header> headers);

    Header map(CreateHeaderRequest request);

    HeaderChild map(CreateHeaderChildRequest request);

    HeaderChildElement map(CreateHeaderChildElementRequest request);
}
