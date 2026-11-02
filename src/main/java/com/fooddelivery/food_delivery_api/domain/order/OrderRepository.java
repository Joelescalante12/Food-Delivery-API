package com.fooddelivery.food_delivery_api.domain.order;

import com.fooddelivery.food_delivery_api.domain.customer.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByCustomer_Id(Long customerId, Pageable pageable);

    Page<Order> findAllByRestaurant_Id(Long restaurantId, Pageable pageable);
}
