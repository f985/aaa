package am.rockstars.security.entity;

import am.rockstars.dto.AddressPayload;
import am.rockstars.entity.Address;
import am.rockstars.entity.User;
import am.rockstars.enums.UserRole;
import am.rockstars.security.util.SecurityUtils;
import am.rockstars.service.AddressService;
import am.rockstars.service.UserService;
import org.springframework.stereotype.Component;

@Component()
public class AddressSecurity {
    final AddressService addressService;
    final UserService userService;

    public AddressSecurity(final AddressService addressService, final UserService userService) {

        this.addressService = addressService;
        this.userService = userService;
    }

    public boolean hasAccessToCreateAddress(AddressPayload payload) {
        if (SecurityUtils.isAnonymous()) {
            return false;
        }
        Long userId = SecurityUtils.getCurrentUserId();
        return isManager(userId) || isOwner(userId, payload);
    }

    public boolean hasAccessToUpdateAddress(Long addressId) {
        if (SecurityUtils.isAnonymous()) {
            return false;
        }
        Long userId = SecurityUtils.getCurrentUserId();
        final Address address = addressService.findById(addressId);
        return isManager(userId) || isOwner(userId, address);
    }

    public boolean hasAccessToGetAddress(Long addressId) {
        if (SecurityUtils.isAnonymous()) {
            return false;
        }
        Long userId = SecurityUtils.getCurrentUserId();
        final Address address = addressService.findById(addressId);
        return isManager(userId) || isOwner(userId, address);
    }

    public boolean hasAccessToDeleteAddress(Long addressId) {
        if (SecurityUtils.isAnonymous()) {
            return false;
        }
        Long userId = SecurityUtils.getCurrentUserId();
        final Address address = addressService.findById(addressId);
        return isManager(userId) || isOwner(userId, address);
    }

    public boolean hasAccessToGetUserAddress(Long userId) {
        if (SecurityUtils.isAnonymous()) {
            return false;
        }
        Long authorizedUser = SecurityUtils.getCurrentUserId();
        return isManager(authorizedUser) || authorizedUser.equals(userId);
    }

    public boolean isManager(final Long userId) {
        final User user = userService.getById(userId);
        return user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.MANAGER;
    }

    public boolean isOwner(final Long userId, final AddressPayload payload) {
        return payload.getUserId().equals(userId);
    }

    public boolean isOwner(final Long userId, final Address addres) {
        return addres.getUser().getId().equals(userId);
    }


}
