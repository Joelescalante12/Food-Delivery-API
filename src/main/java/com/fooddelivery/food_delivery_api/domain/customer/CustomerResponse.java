package com.fooddelivery.food_delivery_api.domain.customer;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Long addressId,
        Boolean isActive
) {
    public CustomerResponse(Customer customer) {
        this(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddressId(),
                customer.getIsActive()
        );
    }
}
