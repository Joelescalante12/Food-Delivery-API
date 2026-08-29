package com.fooddelivery.food_delivery_api.domain.address;

import jakarta.persistence.*;

@Table(name = "address")
@Entity(name = "Address")
public class Address {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    private String AddressLine1;
    private String City;
    private String StateProvince;
    private Integer PostalCode;
    private String Country;


}
