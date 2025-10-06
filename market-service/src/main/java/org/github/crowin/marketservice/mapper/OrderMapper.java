package org.github.crowin.marketservice.mapper;

import org.github.crowin.marketservice.dto.ListData;
import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.dto.order.OrderDto;
import org.github.crowin.marketservice.dto.order.OrderItems;
import org.github.crowin.marketservice.repository.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    OrderDto toDto(Order product);

    default Order toEntity(Long userId, CartDto cart) {
        return Order.builder()
                .items(new OrderItems(cart.items(), cart.totalPrice()))
                .userId(userId)
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
