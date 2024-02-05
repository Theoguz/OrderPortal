package com.example.orderportal.service;

import com.example.orderportal.entity.Product;
import com.example.orderportal.entity.extra.PriceHistory;
import com.example.orderportal.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String name) {
        return productRepository.findByName(name).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Product product) {
        productRepository.save(product);

    }

    public void updateProduct(String name, Product product) {
        Product product1 = productRepository.findByName(name).get();
        PriceHistory newPriceHistory = new PriceHistory();
        newPriceHistory.setPrice(product.getPrice());
        newPriceHistory.setProduct(product1);
        product1.getPriceHistory().add(newPriceHistory);
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());
        productRepository.save(product1);
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

}
