package com.fooddelivery.food_delivery_api.domain.order;

import com.fooddelivery.food_delivery_api.domain.OrderStatus;
import com.fooddelivery.food_delivery_api.domain.customer.Customer;
import com.fooddelivery.food_delivery_api.domain.customer.CustomerRepository;
import com.fooddelivery.food_delivery_api.domain.restaurant.Restaurant;
import com.fooddelivery.food_delivery_api.domain.restaurant.RestaurantRepository;
import com.fooddelivery.food_delivery_api.menu.Menu;
import com.fooddelivery.food_delivery_api.menu.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    private final CustomerRepository customerRepository;

    public OrderService(
            OrderRepository orderRepository,
            RestaurantRepository restaurantRepository,
            MenuRepository menuRepository,
            CustomerRepository customerRepository
    ) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order placeOrder(RecordOrderData data) {

        Customer customer = findCustomer(data.customerId());
        validateCustomerIsActive(customer);

        Restaurant restaurant = findRestaurant(data.restaurantId());
        validateRestaurantIsOpen(restaurant);

        List<Menu> menuItems = findMenuItems(data.items());

        validateMenuItemsBelongToRestaurant(
                menuItems,
                restaurant
        );

        validateMenuItemsAreAvailable(menuItems);

        Map<Long, Menu> menuById =
                mapMenuItemsById(menuItems);
        BigDecimal totalPrice = calculateTotalPrice(data.items());
        Order order = initializeOrder(
                customer,
                restaurant,
                totalPrice
        );

        List<OrderItem> orderItems =
                createOrderItems(
                        data.items(),
                        menuById
                );

        order.setOrderItem(orderItems);

        return orderRepository.save(order);
    }


    private Customer findCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Customer not found"
                        ));
    }

    private void validateCustomerIsActive(Customer customer) {
        if (!customer.getIsActive()) {
            throw new ValidationException(
                    "Customer account is not active"
            );
        }
    }

    private Restaurant findRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Restaurant not found"
                        ));
    }

    private void validateRestaurantIsOpen(
            Restaurant restaurant
    ) {
        if (restaurant.getStatus() == OrderStatus.CLOSED) {
            throw new ValidationException(
                    "Restaurant is closed"
            );
        }
    }

    private List<Menu> findMenuItems(
            List<OrderItemRequest> items
    ) {
        List<Long> ids = items.stream()
                .map(OrderItemRequest::id)
                .distinct()
                .toList();

        List<Menu> menuItems =
                menuRepository.findAllById(ids);

        if (menuItems.size() != ids.size()) {
            throw new EntityNotFoundException(
                    "One or more menu items were not found"
            );
        }

        return menuItems;
    }

    private void validateMenuItemsBelongToRestaurant(
            List<Menu> menuItems,
            Restaurant restaurant
    ) {
        for (Menu menu : menuItems) {
            if (!menu.getRestaurant()
                    .getId()
                    .equals(restaurant.getId())) {

                throw new ValidationException(
                        "Menu item does not belong to restaurant"
                );
            }
        }
    }

    private void validateMenuItemsAreAvailable(
            List<Menu> menuItems
    ) {
        for (Menu menu : menuItems) {
            if (!menu.getIsAvailable()) {
                throw new ValidationException(
                        "Menu item is not available"
                );
            }
        }
    }

    private Map<Long, Menu> mapMenuItemsById(
            List<Menu> menuItems
    ) {
        return menuItems.stream()
                .collect(Collectors.toMap(
                        Menu::getId,
                        menu -> menu
                ));
    }

    private Order initializeOrder(
            Customer customer,
            Restaurant restaurant,
            BigDecimal totalPrice
    ) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setAddress(
                customer.getAddress()
        );
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }

    private List<OrderItem> createOrderItems(
            List<OrderItemRequest> items,
            Map<Long, Menu> menuById
    ) {
        return items.stream()
                .map(item -> {

                    Menu menu = menuById.get(item.id());

                    BigDecimal linePrice =
                            menu.getPrice()
                                    .multiply(
                                            BigDecimal.valueOf(
                                                    item.quantity()
                                            )
                                    );

                    return new OrderItem(
                            item,
                            menu,
                            linePrice
                    );
                })
                .toList();
    }


    private BigDecimal calculateTotalPrice(List<OrderItemRequest> items) {
        return items.stream()
                .map(item -> {
                    var menu = menuRepository.findById(item.id())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Menu item with id " + item.id() + " does not exist"
                            ));

                    return menu.getPrice()
                            .multiply(BigDecimal.valueOf(item.quantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public void acceptOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        order.setStatus(OrderStatus.ACCEPTED);
    }

    @Transactional
    public void declineOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        order.setStatus(OrderStatus.REJECTED);
    }
}
