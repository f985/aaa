package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
