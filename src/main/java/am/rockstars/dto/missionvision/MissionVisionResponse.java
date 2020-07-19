package am.rockstars.dto.missionvision;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MissionVisionResponse {

    private Long id;

    private String content;

    private String heading;

    private String image;

    @JsonProperty(value = "sub_heading")
    private String subHeading;
}
