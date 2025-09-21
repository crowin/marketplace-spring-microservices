package org.github.crowin.userservice.controller;

import lombok.AllArgsConstructor;
import org.github.crowin.userservice.dto.NewUserRequest;
import org.github.crowin.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<Void> register(@RequestBody NewUserRequest newUser) {
//
//    }
}
