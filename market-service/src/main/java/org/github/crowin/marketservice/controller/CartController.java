package org.github.crowin.marketservice.controller;

import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.dto.BasicResponse;
import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.dto.cart.CartProductDto;
import org.github.crowin.marketservice.repository.models.Cart;
import org.github.crowin.marketservice.service.MarketService;
import org.github.crowin.marketservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final MarketService marketService;
    private final ProductService productService;

    @PutMapping("/")
    public BasicResponse<Cart> addToCart(@RequestBody CartProductDto product) {
        var cart = marketService.addToCart(1L, product);
        return BasicResponse.of(cart);
    }

    @DeleteMapping("/product")
    public String removeFromCart(@RequestParam Long productId) {
        marketService.removeFromCart(productId);
        return "removed from cart";
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> clearCart() {
        marketService.clearCart(1L);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public CartDto getCart() {
        return marketService.getCart(1L);
    }
}
