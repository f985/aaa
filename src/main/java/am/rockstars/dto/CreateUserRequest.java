package am.rockstars.dto;

import am.rockstars.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String name;

    private String surname;

    private String email;

    private String password;

    private UserRole role;
}
