package com.sparta.java_02.domain.order.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private final Long orderId;
    private final Long productId;
    private final Integer quantity;
    private final String orderStatus;
    private final LocalDateTime orderedAt;

    // (추후 Service 구현 시)
    // Order 엔티티를 받아 DTO를 생성하는 생성자
    // public OrderResponseDto(Order order) {
    //     this.orderId = order.getId();
    //     this.productId = order.getProductId();
    //     this.quantity = order.getQuantity();
    //     this.orderStatus = order.getStatus().toString();
    //     this.orderedAt = order.getOrderedAt();
    // }
}