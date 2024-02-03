package com.example.orderportal.controller;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Product;
import com.example.orderportal.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addProductToCart(@RequestBody AddToCartRequest request) {
        try {
            cartService.AddProductToCart(request.getCart(), request.getProduct());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}