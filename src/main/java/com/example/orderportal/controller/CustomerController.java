package com.example.orderportal.controller;

import com.example.orderportal.entity.Customer;
import com.example.orderportal.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
            return ResponseEntity.ok("Customer Created " + customer.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
