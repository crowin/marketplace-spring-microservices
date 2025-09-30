package org.github.crowin.marketservice.dto.order;

import lombok.Data;
import org.github.crowin.marketservice.dto.product.ProductDto;

@Data
public final class ItemDto {
    private final ProductDto product;
    private final Integer quantity;
    private Double totalPrice;

    public void updateTotalPrice() {
        this.totalPrice = this.product.price() * this.quantity;
    }

}
