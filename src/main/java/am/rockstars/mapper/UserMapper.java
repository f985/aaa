package am.rockstars.mapper;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.UserBean;
import am.rockstars.entity.User;
import am.rockstars.security.domain.UserPrincipal;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface UserMapper {
    User map(CreateUserRequest user);

    User map(UserBean userBean);

    UserPrincipal map(User user);

    UserBean mapToUserBean(User user);
}
