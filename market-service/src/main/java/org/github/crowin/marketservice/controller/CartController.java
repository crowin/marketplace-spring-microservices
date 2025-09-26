package org.github.crowin.marketservice.controller;

import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.dto.cart.CartProduct;
import org.github.crowin.marketservice.service.MarketService;
import org.github.crowin.marketservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final MarketService marketService;
    private final ProductService productService;

    @PutMapping("/")
    public String addToCart(@RequestBody CartProduct product) {
        marketService.addToCart(product);
        return "added to cart";
    }

    @DeleteMapping("/product")
    public String removeFromCart(@RequestParam Long productId) {
        marketService.removeFromCart(productId);
        return "removed from cart";
    }

    @DeleteMapping("/")
    public String clearCart() {
        marketService.clearCart();
        return "cleared cart";
    }

    @GetMapping("/")
    public CartDto getCart() {
        return marketService.getCart();
    }
}
