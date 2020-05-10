package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}
