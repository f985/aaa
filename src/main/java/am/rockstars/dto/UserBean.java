package am.rockstars.dto;

import am.rockstars.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {
    private String email;

    private String name;

    private String surname;

    private UserRole role;
}
