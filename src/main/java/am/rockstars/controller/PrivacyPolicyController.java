package am.rockstars.controller;

import am.rockstars.dto.privacypolicy.PrivacyPolicyRequest;
import am.rockstars.service.PrivacyPolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PrivacyPolicyController {

    private final PrivacyPolicyService service;

    public PrivacyPolicyController(PrivacyPolicyService service) {
        this.service = service;
    }

    @GetMapping("/privacy-policies")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/admin/privacy-policy/{id}")
    public ResponseEntity<?> delete(@NotNull @PathVariable final Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/privacy-policy")
    public ResponseEntity<?> add(@RequestBody PrivacyPolicyRequest request) {
        service.createPrivacyPolicy(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/privacy-policy/{id}")
    public ResponseEntity<?> edit(@RequestBody PrivacyPolicyRequest request, @PathVariable final Long id) {
        service.edit(request, id);
        return ResponseEntity.ok().build();
    }
}
