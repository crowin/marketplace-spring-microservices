package org.github.crowin.marketservice.controller;

import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.dto.BasicResponse;
import org.github.crowin.marketservice.dto.product.ProductsDto;
import org.github.crowin.marketservice.service.MarketService;
import org.github.crowin.marketservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final MarketService marketService;
    private final ProductService productService;

    @GetMapping
    public BasicResponse<ProductsDto> getProducts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        return BasicResponse.of(productService.getProducts(page, size));
    }
}
