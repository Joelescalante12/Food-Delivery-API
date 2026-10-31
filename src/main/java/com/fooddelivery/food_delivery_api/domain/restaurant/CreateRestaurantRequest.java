package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.address.RecordAddress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRestaurantRequest(
       @NotBlank String name,
       @NotNull RecordAddress address,
       @NotNull RestaurantStatus status,
       @NotNull FulfillmentType fulfillmentType
        ) {
}
