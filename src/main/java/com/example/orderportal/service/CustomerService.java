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

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public void updateCustomer(Customer existingCustomer) {
        customerRepository.save(existingCustomer);
    }

}
