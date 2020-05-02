package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String name;

    private String surname;

    private String email;

    private String token;
}
