package am.rockstars.dto;

import am.rockstars.enums.AddressType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddressPayload {

    @NotNull
    private AddressType type;

    @NotNull
    private String address;

    @NotNull
    private String building;

    @NotNull
    private String street;

    @NotNull
    private String zipcode;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    private long userId;

}
