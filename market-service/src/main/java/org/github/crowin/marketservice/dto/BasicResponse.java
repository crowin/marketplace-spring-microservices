package org.github.crowin.marketservice.dto;

import lombok.Builder;

/*
Basic Response DTO
 */
@Builder
public record BasicResponse<T>(
        T data,
        String error,
        String errorCode
) {
    public static <T> BasicResponse<T> of(T data) {
        return BasicResponse.<T>builder()
                .data(data)
                .build();
    }

    public static BasicResponse<Void> error(String error, String errorCode) {
        return BasicResponse.<Void>builder()
                .error(error)
                .errorCode(errorCode)
                .build();
    }
}
