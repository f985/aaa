package am.rockstars.dto.user;

import am.rockstars.validator.UserPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPasswordResetRequest {
    private String key;
    @UserPassword
    private String password;
}
