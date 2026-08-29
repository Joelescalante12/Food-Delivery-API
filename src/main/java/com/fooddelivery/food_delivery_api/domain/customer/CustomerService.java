package com.fooddelivery.food_delivery_api.domain.customer;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse createCustomer(CreateCustomerRequest data){
        System.out.println("podrontotnmax");
        var customer = customerRepository.save(new Customer(data));
        return new CustomerResponse(customer);
    }


    public CustomerResponse getCustomer(Long id) {

        var customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        return new CustomerResponse(customer);
    }
}
