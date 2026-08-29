package com.fooddelivery.food_delivery_api.domain.order;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
//Tal vez OrderStatus deberia ser un String
public record RecordedOrder(Long id, OrderStatus status, BigDecimal totalPrice, LocalDateTime date) {
    public RecordedOrder(Order order) {
        this(
                order.getId(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt()
        );
    }
}
