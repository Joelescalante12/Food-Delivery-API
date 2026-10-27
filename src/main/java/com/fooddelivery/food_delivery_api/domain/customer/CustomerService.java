package com.fooddelivery.food_delivery_api.domain.customer;

import com.fooddelivery.food_delivery_api.domain.address.MapboxAddressValidatorService;
import com.fooddelivery.food_delivery_api.domain.order.OrderRepository;
import com.fooddelivery.food_delivery_api.domain.order.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MapboxAddressValidatorService mapboxAddressValidatorService;

    public CustomerResponse createCustomer(CreateCustomerRequest data){
        if (data.address() != null) {
            mapboxAddressValidatorService.validate(data.address());
        }

        var customer = customerRepository.save(new Customer(data));
        return new CustomerResponse(customer);
    }


    public CustomerResponse getCustomer(Long id) {

        var customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        return new CustomerResponse(customer);
    }

    public Page<OrderResponse> getAllCustomerOrders(Long customerId, Pageable pagination) {
        return orderRepository.findAllByCustomer_Id(customerId, pagination);
    }
}
