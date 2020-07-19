package am.rockstars.service;

import am.rockstars.dto.AddressPayload;
import am.rockstars.entity.Address;
import am.rockstars.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static am.rockstars.entity.util.Utils.illegalArg;


@Service
@Slf4j
@RequiredArgsConstructor
public class AddressService {
    private final UserService userService;
    private final AddressRepository addressRepository;

    @Transactional
    public long createAddress(final AddressPayload payload) {
        Assert.notNull(payload, "Address payload should not be null");
        log.debug("Creating address for provided payload '{}'", payload);
        final Address address = new Address();
        BeanUtils.copyProperties(payload, address);
        address.setUser(userService.getById(payload.getUserId()));
        return addressRepository.save(address).getId();
    }

    @Transactional
    public void updateAddress(final AddressPayload payload) {
        Assert.notNull(payload, "Address payload should not be null");
        log.debug("Update address for provided payload '{}'", payload);
        final Address address = findById(payload.getId());
        BeanUtils.copyProperties(payload, address);
        addressRepository.save(address);
    }

    public Address findById(final long id) {
        log.debug("Retrieving product by id '{}'", id);
        return addressRepository.findById(id).orElseThrow(illegalArg("Cannot find address by id  -> %s", id));
    }

    public List<Address> findByUserId(final long userId) {
        log.debug("Retrieving addresses by id '{}'", userId);
        return addressRepository.findByUserId(userId);
    }

    public void deleteById(final long id) {
        log.debug("Deleting addresses by id '{}'", id);
        addressRepository.deleteById(id);
    }
}

