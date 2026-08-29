package com.fooddelivery.food_delivery_api.menu;

import com.fooddelivery.food_delivery_api.domain.restaurant.Restaurant;
import com.fooddelivery.food_delivery_api.domain.restaurant.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Page<MenuResponse> getMenu(Long restaurantId, Pageable paginacion){
       return menuRepository.findByRestaurantId(restaurantId,paginacion).map(MenuResponse::new);
    }

    public MenuResponse createMenu(CreateMenuRequest data) {

        var restaurant = findRestaurant(data.restaurantId());

        var menu = menuRepository.save(new Menu(data,restaurant));

        return new MenuResponse(menu);
    }
    private Restaurant findRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Restaurant with id " + restaurantId + " does not exist"
                        ));
    }
}
