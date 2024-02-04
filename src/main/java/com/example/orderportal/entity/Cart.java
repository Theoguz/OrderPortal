package com.example.orderportal.entity;

import com.example.orderportal.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    private int cartTotal;
    private int miktar;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    private String productName;

}
