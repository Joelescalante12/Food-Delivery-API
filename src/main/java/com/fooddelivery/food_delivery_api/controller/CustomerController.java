package com.fooddelivery.food_delivery_api.controller;

import com.fooddelivery.food_delivery_api.domain.customer.CreateCustomerRequest;
import com.fooddelivery.food_delivery_api.domain.customer.CustomerResponse;
import com.fooddelivery.food_delivery_api.domain.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
//TODO: Adaptar addreess cambiaro a la clase y quitarlo de como string
    @PostMapping("create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest data, UriComponentsBuilder uriComponentsBuilder){

        var customer = customerService.createCustomer(data);

        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(customer.id()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customer);
    }


    @GetMapping("{id}")
    public CustomerResponse getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

}
