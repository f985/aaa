package am.rockstars.security.entity;

import am.rockstars.dto.AddressPayload;
import am.rockstars.entity.Address;
import am.rockstars.entity.User;
import am.rockstars.enums.UserRole;
import am.rockstars.security.util.SecurityUtils;
import am.rockstars.service.AddressService;
import am.rockstars.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AddressSecurity {
    final AddressService addressService;
    final UserService userService;

    public AddressSecurity(final AddressService addressService, final UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    public boolean hasAccessToCreateAddress(AddressPayload payload) {
        return hasAccessToUserAddresses(payload.getUserId());
    }

    public boolean hasAccessToUpdateAddress(Long addressId) {
        return hasAccessToAddress(addressId);
    }

    public boolean hasAccessToGetAddress(Long addressId) {
        return hasAccessToAddress(addressId);
    }

    public boolean hasAccessToDeleteAddress(Long addressId) {
        return hasAccessToAddress(addressId);
    }

    public boolean isOwner(final Long userId, final Long addressOwnerId) {
        return Objects.equals(userId, addressOwnerId);
    }

    public boolean hasAccessToGetUserAddress(Long userId) {
        return hasAccessToUserAddresses(userId);
    }

    public boolean hasAccessToAddress(Long addressId) {
        if (SecurityUtils.isAnonymous()) {
            return false;
        }
        Long userId = SecurityUtils.getCurrentUserId();
        final Address address = addressService.findById(addressId);
        return isManager(userId) || isOwner(userId, address.getUser().getId());
    }

    public boolean hasAccessToUserAddresses(final Long userId) {
        if (SecurityUtils.isAnonymous()) {
            return false;
        }
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return isManager(currentUserId) || isOwner(userId, currentUserId);
    }

    public boolean isManager(final Long userId) {
        final User user = userService.getById(userId);
        return user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.MANAGER;
    }

}
