package org.github.crowin.marketservice.dto.cart;

public record CartProduct(
        Long id,
        String title,
        Integer quantity,
        Double price,
        Double totalPrice
) {}
