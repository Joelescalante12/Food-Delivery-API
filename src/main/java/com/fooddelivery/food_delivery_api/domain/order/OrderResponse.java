package com.fooddelivery.food_delivery_api.domain.order;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long id, Long customerId, Long restaurantId, String address, OrderStatus status, BigDecimal totalPrice, LocalDateTime createdAt, LocalDateTime updatedAt ) { }
