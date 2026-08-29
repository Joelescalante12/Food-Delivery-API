package com.fooddelivery.food_delivery_api.domain.order;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;
import com.fooddelivery.food_delivery_api.domain.customer.Customer;
import com.fooddelivery.food_delivery_api.domain.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;  // Your list maps here

    //private Driver DriverId;

//    @OneToOne
//    @JoinColumn(name = "deliveryaddress_id", nullable = false)
//    private Address DeliveryAddress;

    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(RecordOrderData recordData) {

    }
    public void setOrderItem(List<OrderItem> items) {
        this.items = items;
        items.forEach(item -> item.setOrder(this));
    }
}
