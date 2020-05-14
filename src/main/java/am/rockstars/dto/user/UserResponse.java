package am.rockstars.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserResponse {

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    private String mobileNumber;

    private String city;

}
