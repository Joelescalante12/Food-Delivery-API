package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "restaurant")
@Entity(name = "Restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // Points to the row in the addresses table (the Mapbox id lives there).
    private Long addressId;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Enumerated(EnumType.STRING)
    private FulfillmentType fulfillmentType;

    private LocalDateTime createdAt;


    public Restaurant(CreateRestaurantRequest data) {
        this.name = data.name();

        this.status = data.status();
        this.fulfillmentType = data.fulfillmentType();
        this.createdAt = LocalDateTime.now();
    }
}
