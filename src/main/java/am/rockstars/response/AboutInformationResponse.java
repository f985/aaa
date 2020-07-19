package am.rockstars.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AboutInformationResponse {

    private Long id;

    private String content;

    private String heading;

    private String image;

    @JsonProperty(value = "sub_heading")
    private String subHeading;
}
