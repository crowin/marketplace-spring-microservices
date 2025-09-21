package org.github.crowin.userservice.dto;


public record NewUserRequest(
        String username,
        String password
) { }
