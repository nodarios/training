package com.example;


import com.example.model.order.Order;
import com.example.model.user.User;
import com.example.repository.order.OrderRepository;
import com.example.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles({"dev", "qa"})
class MultipleDatasourceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveUserInDB() {
        // GIVEN
        User user = new User();
        user.setEmail("email@gmail.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");

        // WHEN
        User savedUser = userRepository.save(user);

        // THEN
        Optional<User> userOptional = userRepository.findById(savedUser.getId());
        assertTrue(userOptional.isPresent());

    }

    @Test
    void saveOrderDataInDB() {
        // GIVEN
        Order order = new Order();
        order.setProductName("Mobile");
        order.setOrderAmount(100);
        order.setUserId(1);

        // WHEN
        Order savedOrder = orderRepository.save(order);

        // THEN
        Optional<Order> orderOptional = orderRepository.findById(savedOrder.getId());
        assertTrue(orderOptional.isPresent());
    }

}
