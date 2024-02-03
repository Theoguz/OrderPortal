package com.example.orderportal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_table")
public class Order extends BaseEntity{

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String orderCode;
    private int orderTotal;
    private double totalPrice;

    @ManyToOne
    private Customer customer;



}
