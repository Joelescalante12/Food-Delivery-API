package com.fooddelivery.food_delivery_api.domain.address;

public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String message) {
        super(message);
    }
}
