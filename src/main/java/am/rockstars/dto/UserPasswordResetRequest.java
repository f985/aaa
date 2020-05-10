package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordResetRequest {
    private String key;
    private String password;
}
