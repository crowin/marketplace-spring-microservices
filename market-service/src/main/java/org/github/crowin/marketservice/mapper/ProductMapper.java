package org.github.crowin.marketservice.mapper;

import org.github.crowin.marketservice.dto.ListData;
import org.github.crowin.marketservice.dto.product.ProductDto;
import org.github.crowin.marketservice.repository.models.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    default ListData<ProductDto> toProductsDto(Page<Product> page) {
        return new ListData<>(
                page.get().map(this::toDto).toList(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
