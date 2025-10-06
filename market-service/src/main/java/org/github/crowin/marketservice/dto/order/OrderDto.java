package org.github.crowin.marketservice.dto.order;

import org.github.crowin.marketservice.utils.OrderStatus;

import java.time.LocalDateTime;

public record OrderDto(
        Long id,
        String userOrderId,
        LocalDateTime createdAt,
        LocalDateTime finishedAt,
        OrderItems items,
        OrderStatus status) { }