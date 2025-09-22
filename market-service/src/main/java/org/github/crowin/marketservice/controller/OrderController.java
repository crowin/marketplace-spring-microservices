package org.github.crowin.marketservice.controller;

import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.service.MarketService;
import org.github.crowin.marketservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MarketService marketService;

    @PostMapping("/")
    public String placeOrder() {
        marketService.getCart();
        orderService.placeOrder();
        return "placed order";
    }

    @GetMapping("/")
    public String getOrders() {
        orderService.getOrders();
        return "orders";
    }
}
