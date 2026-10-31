package com.fooddelivery.food_delivery_api.domain.restaurant;

import com.fooddelivery.food_delivery_api.domain.address.AddressEntityType;
import com.fooddelivery.food_delivery_api.domain.address.AddressService;
import com.fooddelivery.food_delivery_api.domain.order.OrderRepository;
import com.fooddelivery.food_delivery_api.domain.order.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressService addressService;

    @Transactional
    public RestaurantResponse createRestaurant(@Valid CreateRestaurantRequest data){
        String mapboxId = addressService.resolveMapboxId(data.address());

        Restaurant saved = restaurantRepository.save(new Restaurant(data));

        var address = addressService.create(
                AddressEntityType.RESTAURANT,
                saved.getId(),
                mapboxId,
                data.address().label()
        );
        saved.setAddressId(address.getId());

        return new RestaurantResponse(restaurantRepository.save(saved));
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
