package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;

import java.time.Instant;

public record RestaurantResponse(
        Long id,
        String name,
        OrderStatus status,
        String address,
        FulfillmentType fulfillmentType
        //Instant createdAt
) {

    public RestaurantResponse(Restaurant restaurant) {
        this(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getStatus(),
                restaurant.getAddress(),
                restaurant.getFulfillmentType()
        );
    }

}
