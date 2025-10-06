package org.github.crowin.marketservice.dto.cart;

import org.github.crowin.marketservice.dto.order.ItemDto;

import java.util.List;

public record CartDto(List<ItemDto> items, Double totalPrice) {}