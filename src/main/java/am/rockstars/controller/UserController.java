package am.rockstars.controller;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.EditUserProfileRequest;
import am.rockstars.dto.UserPasswordChangeRequest;
import am.rockstars.dto.UserPasswordResetRequest;
import am.rockstars.exception.InvalidPasswordException;
import am.rockstars.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {
        return ResponseEntity.ok(userService.createUser(user).getActivationKey());
    }

    @PutMapping
    public ResponseEntity<?> editUser(@RequestBody EditUserProfileRequest editedUser) {
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
    public void changePassword(@RequestBody UserPasswordChangeRequest changeRequest) {
        if (changeRequest.getNewPassword().length() < 5) {
            throw new InvalidPasswordException();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.changePassword(auth.getName(), changeRequest.getOldPassword(), changeRequest.getNewPassword());
    }

    @PostMapping(path = "/reset-password/init")
    public String requestPasswordReset(@RequestBody String mail) {
        return userService.requestPasswordReset(mail);
    }

    @PostMapping(path = "/reset-password/finish")
    public void finishPasswordReset(@RequestBody UserPasswordResetRequest resetRequest) {
        if (resetRequest.getPassword().length() < 5) {
            throw new InvalidPasswordException();
        }
        userService.completePasswordReset(resetRequest.getPassword(), resetRequest.getKey());
    }
}
