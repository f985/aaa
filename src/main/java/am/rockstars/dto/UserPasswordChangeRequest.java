package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}
