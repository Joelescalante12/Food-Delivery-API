package com.fooddelivery.food_delivery_api.menu;

import java.math.BigDecimal;

public record MenuResponse(
        Long Id,
        String Name,
        String Description,
        BigDecimal Price) {

    public MenuResponse(Menu menu) {
        this(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice()
        );
    }

    public MenuResponse(MenuResponse menuResponse) {
        this(
                menuResponse.Id,
                menuResponse.Name,
                menuResponse.Description,
                menuResponse.Price()
        );
    }
}
