package org.github.crowin.userservice.dto;

public record TokenAuthResponse(
        String token,
        String type) {}
