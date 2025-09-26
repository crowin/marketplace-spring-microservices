package org.github.crowin.marketservice.mapper;

import org.github.crowin.marketservice.dto.product.ProductDto;
import org.github.crowin.marketservice.dto.product.ProductsDto;
import org.github.crowin.marketservice.repository.models.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    default ProductsDto toProductsDto(Page<Product> page) {
        return new ProductsDto(
                page.get().map(this::toDto).toList(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
