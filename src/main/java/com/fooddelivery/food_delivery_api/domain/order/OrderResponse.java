package com.fooddelivery.food_delivery_api.domain.order;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long id, Long customerId, Long restaurantId, Long addressId, OrderStatus status, BigDecimal totalPrice, LocalDateTime createdAt, LocalDateTime updatedAt )
{
    public OrderResponse(Order order) {
        this(
            order.getId(),
            order.getCustomer().getId(),
            order.getRestaurant().getId(),
            order.getAddressId(),
            order.getStatus(),
            order.getTotalPrice(),
            order.getCreatedAt(),
            order.getUpdatedAt()
        );
    }
}
