package am.rockstars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@ToString
public class AboutInformationPayload {

    @NotNull
    private Long id;

    @NotBlank
    private String content;

    @NotBlank
    private String heading;

    @NotBlank
    private String image;

    @NotBlank
    @JsonProperty(value = "sub_heading")
    private String subHeading;
}
