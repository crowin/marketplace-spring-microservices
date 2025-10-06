package org.github.crowin.marketservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.dto.BasicResponse;
import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.dto.order.ItemDto;
import org.github.crowin.marketservice.repository.models.Cart;
import org.github.crowin.marketservice.service.MarketService;
import org.github.crowin.marketservice.service.ProductService;
import org.github.crowin.marketservice.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "API to work with user cart")
public class CartController {
    private final MarketService marketService;
    private final SecurityUtils securityUtils;

    @Operation(summary = "Add a product to the cart")
    @PutMapping("/")
    public BasicResponse<Cart> addToCart(@RequestBody ItemDto product) {
        var cart = marketService.addToCart(securityUtils.getUserId(), product);
        return BasicResponse.of(cart);
    }

    @Operation(summary = "Remove a product from the cart")
    @DeleteMapping("/product")
    public String removeFromCart(@RequestParam Long productId) {
        marketService.removeFromCart(productId);
        return "removed from cart";
    }

    @Operation(summary = "Clear the cart")
    @DeleteMapping("/")
    public ResponseEntity<Void> clearCart() {
        marketService.clearCart(securityUtils.getUserId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get the cart")
    @GetMapping("/")
    public CartDto getCart() {
        return marketService.getCart(securityUtils.getUserId());
    }
}
