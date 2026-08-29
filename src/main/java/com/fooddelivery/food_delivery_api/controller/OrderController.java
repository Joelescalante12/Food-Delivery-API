package com.fooddelivery.food_delivery_api.controller;



import com.fooddelivery.food_delivery_api.domain.order.OrderService;
import com.fooddelivery.food_delivery_api.domain.order.RecordOrderData;
import com.fooddelivery.food_delivery_api.domain.order.RecordedOrder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody @Valid RecordOrderData recordOrder){

        var order = orderService.placeOrder(recordOrder);

        return ResponseEntity.ok(new RecordedOrder(order));
    }

    @PatchMapping("{orderId}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable Long orderId){
        System.out.println("oooooooo");
        orderService.acceptOrder(orderId);

        return ResponseEntity.ok().build();
    }
    @PatchMapping("{orderId}/reject")
    public ResponseEntity<?> declineOrder(@PathVariable Long orderId){

        orderService.declineOrder(orderId);

        return ResponseEntity.ok().build();
    }

}
