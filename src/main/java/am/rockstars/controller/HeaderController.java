package am.rockstars.controller;

import am.rockstars.dto.CreateHeaderRequest;
import am.rockstars.entity.Header;
import am.rockstars.service.HeaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/header")
public class HeaderController {

    private final HeaderService headerService;

    public HeaderController(HeaderService headerService) {
        this.headerService = headerService;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(headerService.get());
    }

    @PostMapping
    public void add(@RequestBody CreateHeaderRequest request) {
        headerService.addHeader(request);
    }
}
