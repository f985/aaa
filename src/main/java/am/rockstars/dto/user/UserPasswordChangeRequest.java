package am.rockstars.dto.user;

import am.rockstars.validator.UserPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPasswordChangeRequest {
    @UserPassword
    private String oldPassword;
    @UserPassword
    private String newPassword;
}
