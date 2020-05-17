package am.rockstars.controller;

import am.rockstars.dto.AddressPayload;
import am.rockstars.security.util.SecurityUtils;
import am.rockstars.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddress(@Valid @RequestBody final AddressPayload payload) {
        service.createAddress(SecurityUtils.getCurrentUserUsername(), payload);
    }

    @GetMapping
    public List<AddressPayload> getAddressesByEmail(@RequestParam @NotNull final String email) {
        return service.findByUserEmail(email);
    }
}
