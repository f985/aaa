package am.rockstars.dto;

import am.rockstars.validator.EmailNotExist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class UserPasswordResetInitRequest {
    @Email
    @EmailNotExist
    private String email;
}
