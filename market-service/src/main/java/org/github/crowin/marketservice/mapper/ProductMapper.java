package org.github.crowin.marketservice.mapper;

import org.github.crowin.marketservice.dto.ProductDto;
import org.github.crowin.marketservice.repository.models.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDto> toListDto(List<Product> products);

    List<Product> toListEntity(List<ProductDto> productDto);

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
