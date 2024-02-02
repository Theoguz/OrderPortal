package com.example.orderportal.service;

import com.example.orderportal.entity.Customer;
import com.example.orderportal.entity.Order;
import com.example.orderportal.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void PlaceOrder(Order order) {
        orderRepository.save(order);
    }

    public void GetOrderForCode(Order order, String code) {
    }

    public void GetOrderForCustomer(Order order, Customer customer) {

    }
}
