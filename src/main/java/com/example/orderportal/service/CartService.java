package com.example.orderportal.service;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Product;
import com.example.orderportal.repository.CartRepository;
import com.example.orderportal.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public Cart GetCart(Long id) {
        return cartRepository.findById(id).orElse(null);

    }

    public Cart UpdateCart() {
        Cart cart = cartRepository.findById(1L).orElse(null);
        cartRepository.save(cart);
        return cart;
    }

    public Cart EmptyCart() {
        Cart cart = cartRepository.findById(1L).orElse(null);
        cart.setMiktar(0);
        cart.setCartTotal(0);
        cartRepository.save(cart);
        return cart;
    }

    public void AddProductToCart(Cart cart, Product product) {
        if (cart != null && product != null && product.getStock() > 0 && productService.GetProduct(product.getName()) != null) {
            int quantity = cart.getMiktar();
            if (quantity <= product.getStock()) {
                cart.setMiktar((quantity));
                cart.setCartTotal((int) (cart.getCartTotal() + product.getPrice() * quantity));
                product.setStock(product.getStock() - quantity);
                Product existingProduct = productService.GetProduct(product.getName());
                if (existingProduct != null && existingProduct.getStock() > 0 && existingProduct.getStock() >= quantity) {
                    existingProduct.setStock(existingProduct.getStock() - quantity);
                    productService.UpdateProduct(product.getName(), existingProduct);
                    productRepository.save(existingProduct);
                } else {
                    System.out.println("Stoktaki ürün azdır " + existingProduct.getStock());
                }

                cartRepository.save(cart);

            } else {
                System.out.println("Stokta yeterli ürün yok");
            }
        } else {
            System.out.println("Ürün bulunamadı");
        }
    }

    public void RemoveProductFromCart(Cart cart, Product product) {

    }
}

//    public Cart addProductToCart(String name, int miktar) {
//        try {
//            if (cartRepository.findById(1L).isEmpty()) {
//                Cart cart = new Cart();
//                cartRepository.save(cart);
//            }
//        } catch (Exception e) {
//            System.out.println("Sepet bulunamadı");
//        }
//        Cart cart = cartRepository.findById(1L).orElse(null);
//        if (productService.GetProduct(name) == null) {
//            System.out.println("Ürün bulunamadı");
//        }
//        Product product = productService.GetProduct(name);
//        if (miktar <= product.getStock()) {
//            cart.setMiktar(cart.getMiktar() + miktar);
//            product.setStock(product.getStock() - miktar);
//            cart.setCartTotal((int) (cart.getCartTotal() + product.getPrice() * miktar));
//            productRepository.save(product);
//            cartRepository.save(cart);
//        } else {
//            System.out.println("Stokta yeterli ürün yok");
//        }
//        return cart;
//
//    }
