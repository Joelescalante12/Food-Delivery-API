package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;

public record RestaurantResponse(
        Long id,
        String name,
        RestaurantStatus status,
        String address,
        FulfillmentType fulfillmentType
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
