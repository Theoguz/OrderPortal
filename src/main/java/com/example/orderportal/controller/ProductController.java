package com.example.orderportal.controller;


import com.example.orderportal.entity.Product;
import com.example.orderportal.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        Product product = productService.GetProduct(name);
        try {
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürün bulunamadı: " + name);
            } else {
                return ResponseEntity.ok(product);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> CreateProduct(@RequestBody Product product) {
        try {
            productService.CreateProduct(product);
            return ResponseEntity.ok("Product Created "+product.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteProduct(@PathVariable Long id) {
        try {
            productService.DeleteProduct(id);
            return ResponseEntity.ok("Product Deleted"+ id );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<?> UpdateProduct(@PathVariable String name, @RequestBody Product product) {
        productService.UpdateProduct(name, product);
        try {
            return ResponseEntity.ok("Product Updated "+name);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}