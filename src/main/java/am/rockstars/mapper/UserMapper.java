package am.rockstars.mapper;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.EditUserProfileRequest;
import am.rockstars.dto.UserResponse;
import am.rockstars.entity.User;
import am.rockstars.security.domain.UserPrincipal;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface UserMapper {
    User map(CreateUserRequest user);

    UserPrincipal map(User user);

    UserResponse mapToUserResponse(User user);
}
