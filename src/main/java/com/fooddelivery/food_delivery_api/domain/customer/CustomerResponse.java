package com.fooddelivery.food_delivery_api.domain.customer;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String addresses,
        Boolean isActive
) {
    public CustomerResponse(Customer customer) {
        this(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getIsActive()
        );
    }
}
