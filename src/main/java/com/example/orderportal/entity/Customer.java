package com.example.orderportal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    private String name;
    private String address;
    private String phone;
    private String city;

    @OneToOne
    private Cart cart;

    @OneToMany
    private List<Order> order;
}
