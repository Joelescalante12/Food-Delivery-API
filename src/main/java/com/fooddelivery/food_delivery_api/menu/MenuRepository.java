package com.fooddelivery.food_delivery_api.menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<MenuResponse> findByRestaurantId(Long restaurantId, Pageable paginacion);
}
