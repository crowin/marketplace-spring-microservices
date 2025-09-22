package org.github.crowin.marketservice.dto.order;

import org.github.crowin.marketservice.utils.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime finishedAt,
        OrderItems items,
        OrderStatus status) { }