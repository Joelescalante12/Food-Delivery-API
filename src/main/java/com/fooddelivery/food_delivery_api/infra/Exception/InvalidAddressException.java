package com.fooddelivery.food_delivery_api.infra.Exception;

public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String message) {
        super(message);
    }
}
