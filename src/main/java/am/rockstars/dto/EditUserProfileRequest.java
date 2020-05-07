package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditUserProfileRequest {

    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    private String mobileNumber;

    private String location;

}
