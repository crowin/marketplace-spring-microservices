package org.github.crowin.marketservice.repository.models;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.github.crowin.marketservice.dto.order.ItemCartDto;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private Long userId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<ItemCartDto> products;

    private Double totalPrice;
}
