package am.rockstars.controller;

import am.rockstars.dto.AddressPayload;
import am.rockstars.mapper.AddressMapper;
import am.rockstars.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PreAuthorize("@addressSecurity.hasAccessToCreateAddress(#payload)")
    public long createAddress(@Valid @RequestBody final AddressPayload payload) {
        return service.createAddress(payload);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("@addressSecurity.hasAccessToGetUserAddress(#userId)")
    public List<AddressPayload> getAddressesByUserId(@PathVariable final long userId) {
        return service.findByUserId(userId).stream().map(addressMapper::map).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@addressSecurity.hasAccessToUpdateAddress(#id)")
    public void updateAddress(@PathVariable final long id, @Valid @RequestBody final AddressPayload payload) {
        payload.setId(id);
        service.updateAddress(payload);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@addressSecurity.hasAccessToGetAddress(#id)")
    public AddressPayload getAddressById(@PathVariable final long id) {
        return addressMapper.map(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@addressSecurity.hasAccessToDeleteAddress(#id)")
    public void deleteAddressById(@PathVariable final long id) {
        service.deleteById(id);
    }
}
