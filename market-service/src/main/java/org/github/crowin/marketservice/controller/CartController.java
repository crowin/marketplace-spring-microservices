package org.github.crowin.marketservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.dto.BasicResponse;
import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.dto.order.ItemCartDto;
import org.github.crowin.marketservice.service.CartService;
import org.github.crowin.marketservice.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "API to work with user cart")
public class CartController {
    private final CartService cartService;
    private final SecurityUtils securityUtils;

    @Operation(summary = "Add a product to the cart")
    @PutMapping
    public BasicResponse<CartDto> addToCart(@RequestBody ItemCartDto item) {
        var cart = cartService.addToCart(securityUtils.getUserId(), item);
        return BasicResponse.of(cart);
    }

    @Operation(summary = "Remove a product from the cart")
    @DeleteMapping("/product")
    public String removeFromCart(@RequestParam Long productId) {
        cartService.removeFromCart(securityUtils.getUserId(), productId);
        return "removed from cart";
    }

    @Operation(summary = "Clear the cart")
    @DeleteMapping
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart(securityUtils.getUserId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get the cart")
    @GetMapping
    public BasicResponse<CartDto> getCart() {
        var cartDto = cartService.getCart(securityUtils.getUserId());
        return BasicResponse.of(cartDto);
    }
}
