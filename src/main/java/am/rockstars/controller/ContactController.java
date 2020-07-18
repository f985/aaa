package am.rockstars.controller;

import am.rockstars.dto.contact.ContactRequest;
import am.rockstars.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContactController {

    private final ContactService service;

    public ContactController(final ContactService service) {
        this.service = service;
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<?> get(@NotNull @PathVariable final Long id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/contact/{id}")
    public ResponseEntity<?> delete(@NotNull @PathVariable final Long id) {
        return ResponseEntity.ok(service.deleteContact(id));
    }

    @GetMapping("/admin/contact/edit")
    public ResponseEntity<?> getEditableHeaders() {
        return ResponseEntity.ok(service.getEditHeaderResponse());
    }

    @PostMapping("/admin/contact")
    public ResponseEntity<?> add(@RequestBody ContactRequest request) {
        return ResponseEntity.ok(service.createContact(request));
    }

    @PutMapping("/admin/contact/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody ContactRequest request, @PathVariable final Long id) {
        return ResponseEntity.ok(service.editContact(request, id));
    }
}
