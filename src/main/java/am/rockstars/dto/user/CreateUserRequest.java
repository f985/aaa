package am.rockstars.dto.user;

import am.rockstars.validator.UniqueEmail;
import am.rockstars.validator.UserPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CreateUserRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @UniqueEmail
    private String email;

    @UserPassword
    private String password;
}
