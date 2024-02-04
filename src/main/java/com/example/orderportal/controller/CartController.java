package com.example.orderportal.controller;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.extra.AddToCartRequest;
import com.example.orderportal.repository.CartRepository;
import com.example.orderportal.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cart")
public class CartController {

    private final CartService cartService;

    private final CartRepository cartRepository;

    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
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

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id,
                                           @RequestParam int miktar,
                                           @RequestParam String productName) {
        Cart cart = cartRepository.findById(id).orElse(null);

        if (cart != null) {
            cart.setMiktar(miktar);
            cart.setProductName(productName);
            cartRepository.save(cart);

            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}