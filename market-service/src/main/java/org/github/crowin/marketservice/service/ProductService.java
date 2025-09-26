package org.github.crowin.marketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.crowin.marketservice.dto.ListData;
import org.github.crowin.marketservice.dto.product.ProductDto;
import org.github.crowin.marketservice.mapper.ProductMapper;
import org.github.crowin.marketservice.repository.ProductsRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;

    public ListData<ProductDto> getProducts(int page, int size) {
        var products = productsRepository.findAll(Pageable.ofSize(size).withPage(page));
        log.info("Found {} products", products.getTotalElements());
        return productMapper.toProductsDto(products);
    }
}
