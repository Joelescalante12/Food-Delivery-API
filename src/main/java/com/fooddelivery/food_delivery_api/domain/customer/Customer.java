package com.fooddelivery.food_delivery_api.domain.customer;



import com.fooddelivery.food_delivery_api.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
    // User can have an address because this way we can show him restaurants nearby.
    private String address;

    private Boolean isActive;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(CreateCustomerRequest data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.phone = data.phone();
        this.address = data.address() != null ? data.address().toSearchQuery() : null;
        this.isActive = true; // Default active state for new customers
    }
}
