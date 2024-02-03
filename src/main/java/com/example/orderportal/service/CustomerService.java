package com.example.orderportal.service;

import com.example.orderportal.entity.Customer;
import com.example.orderportal.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void AddCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> GetAllCustomers() {
        return customerRepository.findAll();
    }

//    public Customer GetCustomer(String customerId) {
//        return customerRepository.findById(Long.parseLong(customerId)).orElse(null);
//    }
}
