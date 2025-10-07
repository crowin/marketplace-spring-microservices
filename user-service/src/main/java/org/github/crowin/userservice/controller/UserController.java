package org.github.crowin.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.github.crowin.userservice.dto.LoginRequest;
import org.github.crowin.userservice.dto.NewUserRequest;
import org.github.crowin.userservice.dto.TokenAuthResponse;
import org.github.crowin.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Users", description = "API to work with users and tokens")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody NewUserRequest newUser) {
        userService.createUser(newUser);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Login a user")
    @PostMapping("/login")
    public ResponseEntity<TokenAuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        var tokenResp = userService.login(loginRequest);
        return ResponseEntity.ok().body(tokenResp);
    }

    @Operation(summary = "Validate the token along microservices")
    @GetMapping("/validate-token")
    public ResponseEntity<Void> validateToken() {
        return ResponseEntity.ok().build();
    }
}
