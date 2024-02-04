package com.example.orderportal.controller;

import com.example.orderportal.service.CustomerService;
import com.example.orderportal.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping("/place/{id}")
    public ResponseEntity<?> placeOrder(@PathVariable Long id) {
        try {
            orderService.PlaceOrder(id);
            return ResponseEntity.ok("Sipariş alındı");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Sipariş alınamadı");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOrdersForCustomer() {
        try {
            return ResponseEntity.ok(orderService.AllOrders());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Siparişler alınamadı");
        }
    }

    @GetMapping("/get/{orderCode}")
    public ResponseEntity<?> getOrderForCode(@PathVariable String orderCode) {
        try {
            return ResponseEntity.ok(orderService.GetOrderForCode(orderCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Sipariş alınamadı");
        }
    }

    @GetMapping("/getCustomer/{name}")
    public ResponseEntity<?> getCustomerByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersForCustomer(customerService.getCustomerByName(name)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Müşteri bulunamadı");
        }
    }



}
