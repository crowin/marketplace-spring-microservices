package org.github.crowin.userservice.dto;

import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Size(min = 4, max = 25, message = "Username must be between 4 and 25 characters")
        String username,
        @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
        String password
) {
}
