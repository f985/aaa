package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPasswordResetRequest {
    private String key;
    private String password;
}
