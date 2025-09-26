package org.github.crowin.marketservice.mapper;

import org.github.crowin.marketservice.dto.cart.CartDto;
import org.github.crowin.marketservice.repository.models.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto toDto(Cart cart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Cart toCartEntity(CartDto cartDto);

}
