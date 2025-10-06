package org.github.crowin.marketservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.github.crowin.marketservice.dto.BasicResponse;
import org.github.crowin.marketservice.dto.ListData;
import org.github.crowin.marketservice.dto.order.OrderDto;
import org.github.crowin.marketservice.service.MarketService;
import org.github.crowin.marketservice.service.OrderService;
import org.github.crowin.marketservice.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "API to work with user orders")
public class OrderController {
    private final OrderService orderService;
    private final MarketService marketService;
    private final SecurityUtils securityUtils;

    @Operation(summary = "Place a new order")
    @PostMapping("/")
    public BasicResponse<OrderDto> placeOrder() {
        marketService.getCart(securityUtils.getUserId());
        return BasicResponse.of(orderService.placeOrder(securityUtils.getUserId()));
    }

    @Operation(summary = "Get all orders")
    @GetMapping("/")
    public BasicResponse<ListData<OrderDto>> getOrders(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        return BasicResponse.of(orderService.getOrders(securityUtils.getUserId(), size, page));
    }
}
