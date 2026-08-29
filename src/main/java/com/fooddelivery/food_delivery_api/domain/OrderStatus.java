package com.fooddelivery.food_delivery_api.domain;

//Para todos los estados de todas las clases
public enum OrderStatus {
    OPEN,
    CLOSED,

    ///--
    /// ill leave this for later for the menu
    ACTIVE,
    UNAVAILABLE,
    PAUSED,
    ARCHIVED,
    //----

    PENDING,
    ACCEPTED,
    REJECTED
}
