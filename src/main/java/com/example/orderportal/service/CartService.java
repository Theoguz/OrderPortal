package com.example.orderportal.service;

import com.example.orderportal.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void GetCart() {
        cartRepository.findAll();
    }

    public void UpdateCart() {
    }

    public void EmptyCart() {
    }

}
