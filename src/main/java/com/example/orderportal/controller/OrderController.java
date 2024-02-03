package com.example.orderportal.controller;

import com.example.orderportal.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
            return ResponseEntity.ok(orderService.GetAllOrdersForCustomer());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Siparişler alınamadı");
        }
    }

}
