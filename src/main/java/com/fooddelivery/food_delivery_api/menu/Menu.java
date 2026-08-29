package com.fooddelivery.food_delivery_api.menu;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;
import com.fooddelivery.food_delivery_api.domain.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Menu")
@Table(name = "menuitem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isAvailable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Menu(CreateMenuRequest request, Restaurant restaurant) {
        this.restaurant = restaurant;
        this.name = request.name();
        this.description = request.description();
        this.price = request.price();
        this.isAvailable = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
