package org.github.crowin.marketservice.mapper;

import org.github.crowin.marketservice.dto.ListData;
import org.github.crowin.marketservice.dto.order.OrderDto;
import org.github.crowin.marketservice.dto.order.OrderItems;
import org.github.crowin.marketservice.dto.product.ProductDto;
import org.github.crowin.marketservice.repository.models.Cart;
import org.github.crowin.marketservice.repository.models.Order;
import org.github.crowin.marketservice.repository.models.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(Order product);

    default Order toEntity(Cart cart) {
        return Order.builder()
                .items(new OrderItems(cart.getProducts(), cart.getTotalPrice()))
                .userId(cart.getUserId())
                .build();
    }

    default ListData<OrderDto> toOrdersDto(Page<Order> page) {
        return new ListData<>(
                page.get().map(this::toDto).toList(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
