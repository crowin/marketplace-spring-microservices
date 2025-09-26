package org.github.crowin.marketservice.dto.cart;

import lombok.Data;

@Data
public final class CartProductDto {
    private final Long id;
    private final String title;
    private final Integer quantity;
    private final Double price;
    private Double totalPrice;

    public void updateTotalPrice() {
        this.totalPrice = this.price * this.quantity;
    }

}
