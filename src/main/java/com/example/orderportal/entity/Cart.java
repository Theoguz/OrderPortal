package com.example.orderportal.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    private String cartCode;
    private String cartDate;
    private String cartStatus;
    private String cartTotal;


}
