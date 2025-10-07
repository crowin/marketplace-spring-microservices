package org.github.crowin.marketservice.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemCartDto(
        @NotNull @Min(value = 1) Long itemId,
        @Min(value = 1) Integer quantity) {}
