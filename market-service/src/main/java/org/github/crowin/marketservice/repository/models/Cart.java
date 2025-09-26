package org.github.crowin.marketservice.repository.models;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.github.crowin.marketservice.dto.cart.CartProduct;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private Long userId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<CartProduct> products;

    private Double totalPrice;
}
