package com.fooddelivery.food_delivery_api.domain.customer;

public record CreateCustomerRequest(
        String firstName,
        String lastName,
        String email,
        String phone,
        String addresses) {
}
