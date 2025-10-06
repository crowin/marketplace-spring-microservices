package org.github.crowin.marketservice.dto.order;

import lombok.Data;
import org.github.crowin.marketservice.dto.product.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.github.crowin.marketservice.utils.PriceUtils.scalePrice;

@Data
public class ItemDto {
    private final ProductDto product;
    private final Integer quantity;
    private Double totalPrice;

    public void updateTotalPrice() {
        this.totalPrice = scalePrice(this.product.price() * this.quantity);
    }
}
