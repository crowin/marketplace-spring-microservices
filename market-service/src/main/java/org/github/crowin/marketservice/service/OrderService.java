package org.github.crowin.marketservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.crowin.marketservice.dto.ListData;
import org.github.crowin.marketservice.dto.order.OrderDto;
import org.github.crowin.marketservice.mapper.OrderMapper;
import org.github.crowin.marketservice.repository.CartRepository;
import org.github.crowin.marketservice.repository.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDto placeOrder(Long userId) {
        var cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cart not found"));
        var cartDto = cartService.mapCartDto(cart);
        var user_order_id = userId + "_" + orderRepository.countByUserId(userId) + 1;
        var order = orderMapper.toEntity(userId, cartDto);
        order.setUserOrderId(user_order_id);
        log.info("Order prepared for {} user", userId);
        var result = orderRepository.save(order);
        log.info("Order saved for {} user in {} status", userId, result.getStatus());
        cartRepository.deleteByUserId(userId);
        log.info("Cart cleared for {} user", userId);
        return orderMapper.toDto(result);
    }

    public void cancelOrder() {
    }


    public ListData<OrderDto> getOrders(Long userId, int size, int page) {
        var orders = orderRepository.findByUserId(userId, Pageable.ofSize(size).withPage(page));
        return orderMapper.toOrdersDto(orders);
    }
}
