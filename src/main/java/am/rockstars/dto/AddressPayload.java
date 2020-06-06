package am.rockstars.dto;

import am.rockstars.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddressPayload {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private AddressType type;

    @NotNull
    @Size(max = 64)
    private String address;

    @NotNull
    @Size(max = 64)
    private String building;

    @NotNull
    @Size(max = 64)
    private String street;

    @NotNull
    @Size(max = 16)
    private String zipcode;

    @NotNull
    @Size(max = 64)
    private String city;

    @NotNull
    @Size(max = 64)
    private String state;

    @NotNull
    @Size(max = 64)
    private String country;

    @NotNull
    private Long userId;

}
