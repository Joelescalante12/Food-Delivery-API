package com.fooddelivery.food_delivery_api.controller;

import com.fooddelivery.food_delivery_api.menu.CreateMenuRequest;
import com.fooddelivery.food_delivery_api.menu.MenuResponse;
import com.fooddelivery.food_delivery_api.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<Page<MenuResponse>> getMenu(@PathVariable Long restaurantId, @PageableDefault(size = 10, sort = "Name") Pageable pagination){
        Page<MenuResponse> menu = menuService.getMenu(restaurantId, pagination);
        return ResponseEntity.ok(menu);
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuResponse> createMenu(@RequestBody CreateMenuRequest data){

        var response = menuService.createMenu(data);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


}
