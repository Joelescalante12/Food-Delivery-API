package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record CreateRestaurantRequest(
       @NotBlank String name,
       @NotBlank String address,
       @NotNull RestaurantStatus status,
       @NotNull FulfillmentType fulfillmentType
        ) {
}
