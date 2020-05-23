package am.rockstars.service;

import am.rockstars.dto.AddressPayload;
import am.rockstars.entity.Address;
import am.rockstars.exception.ProductNotFoundForIdException;
import am.rockstars.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressService {
    private final UserService userService;
    private final AddressRepository addressRepository;

    @Transactional
    public void createAddress(final String username, final AddressPayload payload) {
        Assert.notNull(payload, "Address payload should not be null");
        log.debug("Creating address for provided payload '{}' username {}", payload, username);
        final Address address = new Address();
        BeanUtils.copyProperties(payload, address);
        address.setUser(userService.getById(payload.getUserId()));
        address.setCreatedBy(userService.getUserByEmail(username));
        address.setCreatedAt(LocalDateTime.now());
        addressRepository.save(address);
    }

    public Address findById(final long id) {
        log.debug("Retrieving product by id '{}'", id);
        return addressRepository.findById(id).orElseThrow(() -> {
            log.warn("Not found address for id {} ", id);
            return new ProductNotFoundForIdException(id);
        });
    }

    public List<Address> findByUserEmail(final String email) {
        log.debug("Retrieving addresses by id '{}'", email);
        return addressRepository.findByUserEmail(email);
    }
}

