package com.example.orderportal.service;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Customer;
import com.example.orderportal.entity.Product;
import com.example.orderportal.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final ProductService productService;

    private final CustomerService customerService;

    public CartService(CartRepository cartRepository, ProductService productService, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public Cart GetCart(Long id) {
        return cartRepository.findById(id).orElse(null);

    }


    public Cart EmptyCart() {
        Cart cart = cartRepository.findById(1L).orElse(null);
        cart.setMiktar(0);
        cart.setCartTotal(0);
        cartRepository.save(cart);
        return cart;
    }

    public void AddProductToCart(Cart cart, Product product, Customer customer) {

        final Logger logger = LoggerFactory.getLogger(CartService.class);

        if (cart != null && product != null && customer != null) {

            // Müşteri var mı kontrol et
            Customer existingCustomer = customerService.getCustomerByName(customer.getName());

            if (existingCustomer != null) {
                // Müşterinin sepeti var mı kontrol et
                if (existingCustomer.getCart() == null) {
                    // Müşterinin sepeti yoksa yeni bir sepet oluştur
                    Cart newCart = new Cart();
                    existingCustomer.setCart(newCart);
                }

                // Ürün var mı kontrol et ve fiyat kontrolü yap
                Product existingProduct = productService.GetProduct(product.getName());

                if (existingProduct != null && existingProduct.getPrice() == product.getPrice()) {
                    // Stok kontrolü yap
                    int quantity = cart.getMiktar();
                    if (existingProduct.getStock() >= quantity) {

                        // Sepeti al
                        Cart customerCart = existingCustomer.getCart();

                        // Sepetteki miktarı güncelle
                        customerCart.setMiktar(quantity + customerCart.getMiktar());

                        // Sepet toplamını güncelle
                        customerCart.setCartTotal((int) (customerCart.getCartTotal() + product.getPrice() * quantity));

                        // Ürün stokunu güncelle
                        existingProduct.setStock(existingProduct.getStock() - quantity);

                        customerCart.setProductName(product.getName());

                        product.setName(product.getName());
                        product.setDescription(product.getDescription());
                        product.setPrice(product.getPrice());
                        product.setStock(product.getStock());
                        // Sepeti ve müşteriyi güncelle
                        cartRepository.save(customerCart);
                        customerService.updateCustomer(existingCustomer);
                        logger.info("Product added to cart successfully. Product: {}, Quantity: {}",
                                product.getName(), quantity);


                    } else {
                        logger.error("Stokta yeterli ürün yok");
                    }
                } else {
                    logger.error("Ürün bulunamadı veya fiyatı değişti");
                }
            } else {
                logger.error("Müşteri bulunamadı");
            }
        } else {
            logger.error("Sepet, ürün veya müşteri bulunamadı");
        }
    }
}