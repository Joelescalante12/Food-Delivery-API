package com.fooddelivery.food_delivery_api.domain.customer;

import com.fooddelivery.food_delivery_api.domain.address.RecordAddress;

public record CreateCustomerRequest(
        String firstName,
        String lastName,
        String email,
        String phone,
        RecordAddress address) {
}
