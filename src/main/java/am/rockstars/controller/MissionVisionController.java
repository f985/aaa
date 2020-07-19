package am.rockstars.controller;

import am.rockstars.dto.missionvision.MissionVisionDataResponse;
import am.rockstars.dto.missionvision.MissionVisionRequest;
import am.rockstars.dto.missionvision.MissionVisionResponse;
import am.rockstars.service.MissionVisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MissionVisionController {

    private final MissionVisionService service;

    public MissionVisionController(MissionVisionService service) {
        this.service = service;
    }

    @GetMapping("/mission-visions")
    public ResponseEntity<?> get() {
        final List<MissionVisionResponse> visionResponses = service.getAll();
        final MissionVisionDataResponse response = new MissionVisionDataResponse(visionResponses);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/admin/mission-vision/{id}")
    public ResponseEntity<?> delete(@NotNull @PathVariable final Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/mission-vision")
    public ResponseEntity<?> add(@RequestBody MissionVisionRequest request) {
        service.createMissionVision(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/mission-vision/{id}")
    public ResponseEntity<?> edit(@RequestBody MissionVisionRequest request, @PathVariable final Long id) {
        service.edit(request, id);
        return ResponseEntity.ok().build();
    }
}
