package com.fooddelivery.food_delivery_api.domain.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecordOrderData(
        @NotNull Long customerId,
        @NotNull Long restaurantId,
        //Should this be name cartItem ? lol
        @NotNull @Valid List<OrderItemRequest> items
) {
}
