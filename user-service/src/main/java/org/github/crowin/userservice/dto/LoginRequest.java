package org.github.crowin.userservice.dto;

public record LoginRequest(
        String username,
        String password
) {
}
