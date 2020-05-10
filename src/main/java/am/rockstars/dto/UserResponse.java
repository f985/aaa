package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserResponse {

    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    private String mobileNumber;

    private String city;

}
