package com.example.orderportal.service;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Customer;
import com.example.orderportal.entity.Order;
import com.example.orderportal.repository.CustomerRepository;
import com.example.orderportal.repository.OrderRepository;
import com.example.orderportal.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, CartService cartService, CustomerRepository customerRepository, ProductRepository productRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.cartService = cartService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }


    public void PlaceOrder(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        Cart cart = cartService.GetCart(id);
        if (cart == null) {
            System.out.println("Sepetiniz boş");
        } else {
            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderTotal(cart.getCartTotal());
            order.setTotalPrice(cart.getCartTotal());
            orderRepository.save(order);
            cartService.EmptyCart();
        }

    }

    public Order GetOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);

    }

    public List<Order> AllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersForCustomer(String customer) {


    }


}


