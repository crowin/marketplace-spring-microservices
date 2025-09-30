package org.github.crowin.marketservice.dto.order;

import java.util.List;

public record OrderItems(List<ItemDto> products, Double totalPrice) {}
