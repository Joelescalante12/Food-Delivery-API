package com.fooddelivery.food_delivery_api.domain.order;

import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
        @NotNull Long id,
        @NotNull Integer quantity) {
}
