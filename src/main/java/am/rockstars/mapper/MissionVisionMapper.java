package am.rockstars.mapper;

import am.rockstars.dto.missionvision.MissionVisionRequest;
import am.rockstars.dto.missionvision.MissionVisionResponse;
import am.rockstars.entity.MissionVision;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface MissionVisionMapper {
    List<MissionVisionResponse> map(List<MissionVision> missionVisions);

    MissionVision map(MissionVisionRequest request);
}
