package com.example.orderportal.service;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Customer;
import com.example.orderportal.entity.Order;
import com.example.orderportal.repository.CustomerRepository;
import com.example.orderportal.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, CartService cartService, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.cartService = cartService;
        this.customerRepository = customerRepository;

    }


    public void placeOrder(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        Cart cart = cartService.getCart(id);
        if (cart == null) {
            System.out.println("Sepetiniz boş");
        } else {
            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderTotal(cart.getCartTotal());
            order.setTotalPrice(cart.getCartTotal());
            orderRepository.save(order);
            cartService.emptyCart();
        }

    }

    public Order getOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);

    }

    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersForCustomer(Customer customer) {
        customerService.getCustomerByName(customer.getName());
        return customer.getOrder();
    }


}


