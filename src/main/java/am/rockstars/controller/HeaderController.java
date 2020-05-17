package am.rockstars.controller;

import am.rockstars.dto.header.CreateHeaderChildElementRequest;
import am.rockstars.dto.header.CreateHeaderChildRequest;
import am.rockstars.dto.header.CreateHeaderRequest;
import am.rockstars.service.HeaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HeaderController {

    private final HeaderService headerService;

    public HeaderController(HeaderService headerService) {
        this.headerService = headerService;
    }

    @GetMapping("/header")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(headerService.get());
    }

    @DeleteMapping("/admin/header/{headerId}")
    public ResponseEntity<?> delete(@PathVariable final Long headerId) {
        return ResponseEntity.ok(headerService.deleteHeader(headerId));
    }

    @DeleteMapping("/admin/header/child/{childId}")
    public ResponseEntity<?> deleteChild(@PathVariable final Long childId) {
        return ResponseEntity.ok(headerService.deleteChild(childId));
    }

    @DeleteMapping("/admin/header/child/element/{childElementId}")
    public ResponseEntity<?> deleteChildElement(@PathVariable final Long childElementId) {
        return ResponseEntity.ok(headerService.deleteChildElement(childElementId));
    }

    @GetMapping("/admin/header/edit-response")
    public ResponseEntity<?> getEdit() {
        return ResponseEntity.ok(headerService.getEditResponse());
    }

    @PostMapping("/admin/header/")
    public ResponseEntity<?> add(@RequestBody CreateHeaderRequest request) {
        return ResponseEntity.ok(headerService.addHeader(request));
    }

    @PostMapping("/admin/header/{headerId}/child")
    public ResponseEntity<?> addChild(@RequestBody CreateHeaderChildRequest request, @PathVariable final Long headerId) {
        return ResponseEntity.ok(headerService.addHeaderChild(request, headerId));
    }

    @PostMapping("/admin/header/child/{childId}/element")
    public ResponseEntity<?> addChildElement(@RequestBody CreateHeaderChildElementRequest request, @PathVariable final Long childId) {
        return ResponseEntity.ok(headerService.addHeaderChildElement(request, childId));
    }

}
