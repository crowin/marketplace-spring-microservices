package org.github.crowin.marketservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.dto.BasicResponse;
import org.github.crowin.marketservice.dto.ListData;
import org.github.crowin.marketservice.dto.product.ProductDto;
import org.github.crowin.marketservice.service.CartService;
import org.github.crowin.marketservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "API to work with items")
public class ProductsController {
    private final CartService cartService;
    private final ProductService productService;

    @Operation(summary = "Get all items")
    @GetMapping
    public BasicResponse<ListData<ProductDto>> getProducts(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        return BasicResponse.of(productService.getProducts(page, size));
    }
}
