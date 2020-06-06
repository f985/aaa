package am.rockstars.controller;

import am.rockstars.dto.AddressPayload;
import am.rockstars.mapper.AddressMapper;
import am.rockstars.security.util.SecurityUtils;
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
    @PreAuthorize(value = "hasAuthority('MANAGER') or authentication.principal.equals(#payload.userId)")
    public void createAddress(@Valid @RequestBody final AddressPayload payload) {
        SecurityUtils.getCurrentUserUsername();
        service.createAddress(payload);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize(value = "hasAuthority('MANAGER') or authentication.principal.equals(#userId)")
    public List<AddressPayload> getAddressesByUserId(@PathVariable final long userId) {
        return service.findByUserId(userId).stream().map(addressMapper::map).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AddressPayload getAddressById(@PathVariable final long id) {
        return addressMapper.map(service.findById(id));
    }
}
