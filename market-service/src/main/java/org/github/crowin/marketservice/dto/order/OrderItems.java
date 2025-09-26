package org.github.crowin.marketservice.dto.order;

import org.github.crowin.marketservice.dto.product.ProductDto;

import java.util.List;

public record OrderItems(List<ProductDto> products, Double totalPrice) {}
