package org.github.crowin.userservice.controller;

import lombok.AllArgsConstructor;
import org.github.crowin.userservice.dto.LoginRequest;
import org.github.crowin.userservice.dto.NewUserRequest;
import org.github.crowin.userservice.dto.TokenAuthResponse;
import org.github.crowin.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody NewUserRequest newUser) {
        userService.createUser(newUser);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenAuthResponse> login(@RequestBody LoginRequest loginRequest) {
        var tokenResp = userService.login(loginRequest);
        return ResponseEntity.ok().body(tokenResp);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from user service!");
    }
}
