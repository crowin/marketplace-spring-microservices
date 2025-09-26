package org.github.crowin.marketservice.dto.product;

import java.util.List;

public record ProductsDto(
        List<ProductDto> products,
        Integer currentPage,
        Integer totalPages,
        Long totalItems
) { }
