package com.example.orderportal.controller;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    private Cart cart;
    private Product product;


}
