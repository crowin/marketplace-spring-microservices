//package org.github.crowin.marketservice.repository.models;
//
//import com.vladmihalcea.hibernate.type.json.JsonType;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.github.crowin.marketservice.dto.order.OrderItems;
//import org.github.crowin.marketservice.utils.OrderStatus;
//import org.hibernate.annotations.Type;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "orders")
//@Getter @Setter
//public class Order {
//    @Id
//    @GeneratedValue
//    @Column(columnDefinition = "UUID")
//    private UUID id;
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_order_id", unique = true, nullable = false, updatable = false)
//    private Long userOrderId;
//
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    private String title;
//    private Double price;
//
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(name = "finished_at")
//    private LocalDateTime finishedAt;
//
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status;
//
//    @Type(JsonType.class)
//    @Column(columnDefinition = "jsonb")
//    private OrderItems items;
//}
