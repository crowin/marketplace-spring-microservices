package org.github.crowin.marketservice.repository.models;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.github.crowin.marketservice.dto.order.OrderItems;
import org.github.crowin.marketservice.utils.OrderStatus;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Long userId;

    private String userOrderId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private OrderItems items;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime finishedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, insertable = false)
    private OrderStatus status;
}
