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

    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private String orderCode;
    private String orderDate;
    private String orderStatus;
    private String orderTotal;


}
