package com.fooddelivery.food_delivery_api.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateMenuRequest(
        @NotNull Long restaurantId,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @Positive BigDecimal price) {
}
