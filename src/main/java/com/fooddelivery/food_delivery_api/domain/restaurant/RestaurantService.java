package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.order.OrderRepository;
import com.fooddelivery.food_delivery_api.domain.order.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    OrderRepository orderRepository;

    public RestaurantResponse createRestaurant(@Valid CreateRestaurantRequest data){

        Restaurant saved = restaurantRepository.save(new Restaurant(data));

        return new RestaurantResponse(saved);
    }

    public Page<RestaurantResponse> getAllRestaurants(Pageable paginacion){
        return restaurantRepository.findAll(paginacion)
                .map(RestaurantResponse::new);
    }

    public RestaurantResponse getRestaurant(Long id) {
        var restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
        return new RestaurantResponse(restaurant);
    }

    public Page<OrderResponse> getAllRestaurantOrder(Long restaurantId, Pageable pagination) {

        return orderRepository.findAllByRestaurant_Id(restaurantId,pagination);

    }
}
