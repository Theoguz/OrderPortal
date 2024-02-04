package com.example.orderportal.entity;

import com.example.orderportal.entity.base.BaseEntity;
import com.example.orderportal.entity.extra.PriceHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    private String name;
    private String description;
    private double price;
    private int stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("product")
    private List<PriceHistory> priceHistory = new ArrayList<>();

}
