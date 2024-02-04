package com.example.orderportal.entity.exterentity;

import com.example.orderportal.entity.Product;
import com.example.orderportal.entity.baseentity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistory extends BaseEntity {

    @ManyToOne
    private Product product;
}
