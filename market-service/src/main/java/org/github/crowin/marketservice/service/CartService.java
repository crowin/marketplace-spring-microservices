package org.github.crowin.marketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.dto.order.ItemCartDto;
import org.github.crowin.marketservice.dto.order.ItemDto;
import org.github.crowin.marketservice.mapper.ProductMapper;
import org.github.crowin.marketservice.repository.CartRepository;
import org.github.crowin.marketservice.repository.ProductsRepository;
import org.github.crowin.marketservice.repository.models.Cart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.github.crowin.marketservice.utils.PriceUtils.scalePrice;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;

    @Transactional
    public CartDto addToCart(Long userId, ItemCartDto product) {
        var cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> Cart.builder()
                        .userId(userId)
                        .totalPrice(0.0)
                        .products(new ArrayList<>())
                        .build());
        cart.getProducts().add(product);
        log.info("Cart updated for {} user", userId);
        var savedCart = cartRepository.save(cart);

        return mapCartDto(savedCart);
    }

    @Transactional
    public void removeFromCart(Long userId, long itemId) {
        cartRepository.findByUserId(userId)
                .ifPresentOrElse(c -> {
                    c.getProducts().removeIf(item -> item.itemId() == itemId);
                    cartRepository.save(c);
                    log.info("{} item was removed from the cart of {} user", itemId, userId);
                    }, () -> log.warn("{} item wasn't found in the cart of {} user", itemId, userId)
                );
    }

    @Transactional
    public void clearCart(Long userId) {
        if (cartRepository.deleteByUserId(userId).isPresent()) {
            log.info("Cart cleared for {} user", userId);
        } else {
            log.warn("Cart for {} user not found", userId);
        }
    }

    public CartDto getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .map(this::mapCartDto)
                .orElse(new CartDto(List.of(), 0.0));
    }


    public CartDto mapCartDto(Cart cart) {
        var totalPrice = 0.0;
        var items = new ArrayList<ItemDto>();
        for (var item : cart.getProducts()) {
            var product = productsRepository.findById(item.itemId());
            if (product.isEmpty()) {
                log.warn("Product with id {} not found", item.itemId());
                continue;
            }
            var productDto = productMapper.toDto(product.get());
            var itemDto = new ItemDto(productDto, item.quantity());
            itemDto.updateTotalPrice();
            items.add(itemDto);
            totalPrice += itemDto.getTotalPrice();
        }
        log.info("Cart is prepared for {} user", cart.getUserId());
        return new CartDto(items, scalePrice(totalPrice));
    }
}
