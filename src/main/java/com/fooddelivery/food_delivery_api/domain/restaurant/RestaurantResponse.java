package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;

public record RestaurantResponse(
        Long id,
        String name,
        RestaurantStatus status,
        Long addressId,
        FulfillmentType fulfillmentType
) {

    public RestaurantResponse(Restaurant restaurant) {
        this(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getStatus(),
                restaurant.getAddressId(),
                restaurant.getFulfillmentType()
        );
    }

}
