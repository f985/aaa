package am.rockstars.dto.user;

import am.rockstars.validator.UniqueEmail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class UserPasswordResetInitRequest {
    @Email
    @UniqueEmail
    private String email;
}
