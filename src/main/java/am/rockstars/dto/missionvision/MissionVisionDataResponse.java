package am.rockstars.dto.missionvision;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MissionVisionDataResponse {

    @JsonProperty(value = "mission_vision")
    private List<MissionVisionResponse> missionVisionResponseList;

    public MissionVisionDataResponse(final List<MissionVisionResponse> missionVisionResponseList) {
        this.missionVisionResponseList = missionVisionResponseList;
    }
}
