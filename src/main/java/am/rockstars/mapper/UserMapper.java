package am.rockstars.mapper;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,componentModel = "spring")
public interface UserMapper {
    User map(CreateUserRequest user);
}