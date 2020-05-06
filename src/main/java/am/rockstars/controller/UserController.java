package am.rockstars.controller;

import am.rockstars.dto.CreateUserRequest;
import am.rockstars.dto.UserBean;
import am.rockstars.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping
    public void editUser(@RequestBody UserBean editedUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.editUser(auth.getName(), editedUser);
    }

    @GetMapping(value = "/current-user")
    public ResponseEntity<?> getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getUserByEmail(auth.getName()));
    }
}
