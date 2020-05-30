package am.rockstars.controller;

import am.rockstars.dto.header.edit.CreateHeaderChildElementRequest;
import am.rockstars.dto.header.edit.CreateHeaderChildRequest;
import am.rockstars.dto.header.edit.CreateHeaderRequest;
import am.rockstars.service.HeaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HeaderController {

    private final HeaderService headerService;

    public HeaderController(HeaderService headerService) {
        this.headerService = headerService;
    }

    @GetMapping("/header")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(headerService.getAll());
    }

    @DeleteMapping("/admin/header/{headerId}")
    public ResponseEntity<?> delete(@NotNull @PathVariable final Long headerId) {
        return ResponseEntity.ok(headerService.deleteHeader(headerId));
    }

    @DeleteMapping("/admin/header/child/{childId}")
    public ResponseEntity<?> deleteChild(@NotNull @PathVariable final Long childId) {
        return ResponseEntity.ok(headerService.deleteChild(childId));
    }

    @DeleteMapping("/admin/header/child/element/{childElementId}")
    public ResponseEntity<?> deleteChildElement(@PathVariable final Long childElementId) {
        return ResponseEntity.ok(headerService.deleteChildElement(childElementId));
    }

    @GetMapping("/admin/header/edit")
    public ResponseEntity<?> getEditableHeaders() {
        return ResponseEntity.ok(headerService.getEditHeaderResponse());
    }

    @PostMapping("/admin/header")
    public ResponseEntity<?> add(@RequestBody CreateHeaderRequest request) {
        return ResponseEntity.ok(headerService.addHeader(request));
    }

    @PostMapping("/admin/header/{headerId}/child")
    public ResponseEntity<?> addChild(@Valid @RequestBody CreateHeaderChildRequest request, @NotNull @PathVariable final Long headerId) {
        return ResponseEntity.ok(headerService.addHeaderChild(request, headerId));
    }

    @PostMapping("/admin/header/child/{childId}/element")
    public ResponseEntity<?> addChildElement(@Valid @RequestBody CreateHeaderChildElementRequest request, @NotNull @PathVariable final Long childId) {
        return ResponseEntity.ok(headerService.addHeaderChildElement(request, childId));
    }

}
