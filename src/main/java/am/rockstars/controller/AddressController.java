package am.rockstars.controller;

import am.rockstars.dto.AddressPayload;
import am.rockstars.mapper.AddressMapper;
import am.rockstars.security.util.SecurityUtils;
import am.rockstars.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService service;
    private final AddressMapper addressMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddress(@Valid @RequestBody final AddressPayload payload) {
        service.createAddress(SecurityUtils.getCurrentUserUsername(), payload);
    }

    @GetMapping("/{email}")
    public List<AddressPayload> getAddressesByEmail(@PathVariable @NotNull final String email) {
        return service.findByUserEmail(email).stream().map(addressMapper::map).collect(Collectors.toList());
    }

    @GetMapping
    public AddressPayload getAddressById(@RequestParam @NotNull final long id) {
        return addressMapper.map(service.findById(id));
    }
}
