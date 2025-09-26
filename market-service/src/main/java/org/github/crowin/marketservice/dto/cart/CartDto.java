package org.github.crowin.marketservice.dto.cart;

import java.util.List;

public record CartDto(
        List<CartProductDto> products,
        Double totalPrice
) {}
