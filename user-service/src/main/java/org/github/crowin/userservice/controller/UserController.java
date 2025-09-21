package org.github.crowin.userservice.controller;

import lombok.AllArgsConstructor;
import org.github.crowin.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<Void> register(@RequestBody NewUserRequest newUser) {
//
//    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from user service!");
    }
}
