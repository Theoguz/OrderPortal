package com.example.orderportal.service;

import com.example.orderportal.entity.Cart;
import com.example.orderportal.entity.Customer;
import com.example.orderportal.entity.Product;
import com.example.orderportal.repository.CartRepository;
import com.example.orderportal.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final CustomerService customerService;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, ProductService productService, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.customerService = customerService;
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

    public void AddProductToCart(Cart cart, Product product, Customer customer) {

        if (cart != null && product != null && customer != null) {
            // Ürün var mı kontrol et
            Product existingProduct = productService.GetProduct(product.getName());

            if (existingProduct != null) {
                // Stok kontrolü yap
                int quantity = cart.getMiktar();
                if (existingProduct.getStock() >= quantity && existingProduct.getPrice()==product.getPrice()) {
                    // Müşteriyi kontrol et
                    Customer existingCustomer = customerService.getCustomerByName(customer.getName());

                    if (existingCustomer != null) {
                        // Müşterinin sepeti var mı kontrol et
                        if (existingCustomer.getCart() == null) {
                            // Müşterinin sepeti yoksa yeni bir sepet oluştur
                            Cart newCart = new Cart();
                            existingCustomer.setCart(newCart);
                        }

                        // Sepeti al
                        Cart customerCart = existingCustomer.getCart();

                        // Sepetteki miktarı güncelle
                        customerCart.setMiktar(quantity+customerCart.getMiktar());

                        // Sepet toplamını güncelle
                        customerCart.setCartTotal((int) (customerCart.getCartTotal() + product.getPrice() * quantity));

                        // Ürün stokunu güncelle
                        existingProduct.setStock(existingProduct.getStock() - quantity);
                        productRepository.save(existingProduct);

                        // Sepeti ve müşteriyi güncelle
                        cartRepository.save(customerCart);
                        customerService.updateCustomer(existingCustomer);
                    } else {
                        System.out.println("Müşteri bulunamadı");
                    }
                } else {
                    System.out.println("Stokta yeterli ürün yok veya fiyat değişti");
                }
            } else {
                System.out.println("Ürün bulunamadı");
            }
        } else {
            System.out.println("Sepet, ürün veya müşteri bulunamadı");
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
