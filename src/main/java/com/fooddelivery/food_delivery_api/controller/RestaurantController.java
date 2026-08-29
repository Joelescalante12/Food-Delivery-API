package com.fooddelivery.food_delivery_api.controller;

import com.fooddelivery.food_delivery_api.domain.restaurant.CreateRestaurantRequest;
import com.fooddelivery.food_delivery_api.domain.restaurant.RestaurantResponse;
import com.fooddelivery.food_delivery_api.domain.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/restaurant/create")
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest data, UriComponentsBuilder uriComponentsBuilder){
        var restaurant = restaurantService.createRestaurant(data);

        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(restaurant.id()).toUri();

        return ResponseEntity.created(uri).body(restaurant);
    }

    @GetMapping("restaurants")
    public ResponseEntity<Page<RestaurantResponse>> getAllRestaurants(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        return ResponseEntity.ok(restaurantService.getAllRestaurants(pagination));
    }

    @GetMapping("/restaurant/{id}")
    public RestaurantResponse getRestaurant(@PathVariable Long id){
        return restaurantService.getRestaurant(id);
    }




}
