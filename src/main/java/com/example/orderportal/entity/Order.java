package com.example.orderportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_table")
public class Order extends BaseEntity {

    private String orderCode = UUID.randomUUID().toString();
    private int orderTotal;
    private double totalPrice;
    @ManyToOne
    private Customer customer;


}
