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
            cartService.AddProductToCart(request.getCart(), request.getProduct(), request.getCustomer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/get")
    public Cart getCart(@RequestParam Long id) {
        try {
            return cartService.GetCart(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @PostMapping("/update")
    public Cart updateCart() {
        try {
            return cartService.UpdateCart();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}