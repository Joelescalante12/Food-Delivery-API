package com.fooddelivery.food_delivery_api.domain.order;

import com.fooddelivery.food_delivery_api.menu.Menu;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    
    private Integer quantity;

    private BigDecimal price;

    public OrderItem(OrderItemRequest item, Menu menu, BigDecimal price) {
        this.menu = menu;
        this.quantity = item.quantity();
        this.price = price;
    }
}
