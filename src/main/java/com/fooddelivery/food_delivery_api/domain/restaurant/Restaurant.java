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
    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    //TODO Cambiar el Order Status por FulfillmentType
    @Enumerated(EnumType.STRING)
    private FulfillmentType fulfillmentType;

    private LocalDateTime createdAt;


    public Restaurant(CreateRestaurantRequest data) {
        this.name = data.name();
        this.address = data.address();
        this.status = data.status();
        this.fulfillmentType = data.fulfillmentType();
        this.createdAt = LocalDateTime.now();
    }
}
