package com.example.orderportal.service;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Product;
import com.example.orderportal.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product GetProduct(String name) {
        return productRepository.findByName(name).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void CreateProduct(Product product) {
        productRepository.save(product);

    }

    public void UpdateProduct(String name, Product product) {
        Product product1 = productRepository.findByName(name).get();
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());
        productRepository.save(product1);
    }


    public void DeleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    //Can be Fixed son 2 fonksiyon//
    public void AddProductToCart(Cart cart, Product product) {

    }

    public void RemoveProductFromCart(Cart cart, Product product) {
    }


}
