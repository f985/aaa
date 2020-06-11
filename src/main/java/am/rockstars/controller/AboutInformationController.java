package am.rockstars.controller;

import am.rockstars.dto.AboutInformationPayload;
import am.rockstars.response.AboutInformationResponse;
import am.rockstars.service.AboutInformationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AboutInformationController {
    private final AboutInformationService aboutInformationService;

    public AboutInformationController(AboutInformationService aboutInformationService) {
        this.aboutInformationService = aboutInformationService;
    }

    @GetMapping("/about/{id}")
    public AboutInformationResponse get(@PathVariable Long id) {
       return aboutInformationService.get(id);
    }

    @PutMapping("/admin/about")
    public void update (@Valid @RequestBody AboutInformationPayload payload) {
        aboutInformationService.update(payload);
    }
}
