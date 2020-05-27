package am.rockstars.dto.user;

import am.rockstars.validator.UniqueEmail;
import am.rockstars.validator.UserPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class CreateUserRequest {
    private String firstName;

    private String lastName;

    @Email
    @UniqueEmail
    private String email;

    @UserPassword
    private String password;
}
