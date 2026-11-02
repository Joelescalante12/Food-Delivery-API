package com.fooddelivery.food_delivery_api.domain.customer;

import com.fooddelivery.food_delivery_api.domain.address.AddressEntityType;
import com.fooddelivery.food_delivery_api.domain.address.AddressService;
import com.fooddelivery.food_delivery_api.domain.order.OrderRepository;
import com.fooddelivery.food_delivery_api.domain.order.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressService addressService;

    @Transactional
    public CustomerResponse createCustomer(CreateCustomerRequest data){
        String mapboxId = addressService.resolveMapboxId(data.address());

        var customer = customerRepository.save(new Customer(data));

        var address = addressService.create(
                AddressEntityType.CUSTOMER,
                customer.getId(),
                mapboxId,
                data.address().label()
        );
        customer.setAddressId(address.getId());

        return new CustomerResponse(customerRepository.save(customer));
    }


    public CustomerResponse getCustomer(Long id) {

        var customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        return new CustomerResponse(customer);
    }

    public Page<OrderResponse> getAllCustomerOrders(Long customerId, Pageable pagination) {
        return orderRepository.findAllByCustomer_Id(customerId, pagination).map(OrderResponse::new);
    }
}
