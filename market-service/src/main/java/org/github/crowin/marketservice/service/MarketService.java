package org.github.crowin.marketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.dto.cart.CartProductDto;
import org.github.crowin.marketservice.mapper.CartMapper;
import org.github.crowin.marketservice.mapper.ProductMapper;
import org.github.crowin.marketservice.repository.CartRepository;
import org.github.crowin.marketservice.repository.models.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service @RequiredArgsConstructor @Slf4j
public class MarketService {
    private final CartRepository cartRepository;
    private final ProductMapper productMapper;
    private final CartMapper cartMapper;

    @Transactional
    public Cart addToCart(Long userId, CartProductDto product) {
        var cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> Cart.builder()
                        .userId(userId)
                        .totalPrice(0.0)
                        .products(new ArrayList<>())
                        .build());
        calculateAndAAddProduct(cart, product);
        log.info("Cart updated for {} user", userId);
        return cartRepository.save(cart);
    }

    public void removeFromCart(long productId) {
    }

    public void clearCart(Long userId) {
        if (cartRepository.deleteByUserId(userId)) {
            log.info("Cart cleared for {} user", userId);
        } else {
            log.warn("Cart for {} user not found", userId);
        }
    }

    public CartDto getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .map(cartMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }


    private void calculateAndAAddProduct(Cart cart, CartProductDto product) {
        product.updateTotalPrice();
        cart.getProducts().add(product);
        cart.setTotalPrice(cart.getTotalPrice() + product.getTotalPrice());
    }
}
