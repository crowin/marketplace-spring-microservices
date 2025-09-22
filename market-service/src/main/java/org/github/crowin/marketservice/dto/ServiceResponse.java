package org.github.crowin.marketservice.dto;

import lombok.Builder;

@Builder
public record ServiceResponse<T>(
        T data,
        String error,
        Integer errorCode
) {}
