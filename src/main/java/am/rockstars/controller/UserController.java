package am.rockstars.controller;

import am.rockstars.dto.*;
import am.rockstars.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest user) {
        return ResponseEntity.ok(userService.createUser(user).getActivationKey());
    }

    @PutMapping
    public ResponseEntity<?> editUser(@Valid @RequestBody EditUserProfileRequest editedUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.editUserProfile(auth.getName(), editedUser));
    }

    @GetMapping(value = "/current-user")
    public ResponseEntity<?> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getUserResponseByEmail(auth.getName()));
    }

    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        userService.activateRegistration(key);
    }

    @PostMapping(path = "/change-password")
    public void changePassword(@Valid @RequestBody UserPasswordChangeRequest changeRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.changePassword(auth.getName(), changeRequest.getOldPassword(), changeRequest.getNewPassword());
    }

    @PostMapping(path = "/reset-password/init")
    public String requestPasswordReset(@Valid @RequestBody UserPasswordResetInitRequest resetInitRequest) {
        return userService.requestPasswordReset(resetInitRequest.getEmail());
    }

    @PostMapping(path = "/reset-password/finish")
    public void finishPasswordReset(@Valid @RequestBody UserPasswordResetRequest resetRequest) {
        userService.completePasswordReset(resetRequest.getPassword(), resetRequest.getKey());
    }
}
