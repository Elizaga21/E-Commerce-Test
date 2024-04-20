package com.proyecto.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyecto.entities.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CartService {
    private final Map<String, Cart> carts = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Cart createCart(String id) {
        Cart cart = new Cart(id);
        carts.put(id, cart);
        
        scheduler.schedule(() -> deleteCart(id), 10, TimeUnit.MINUTES);
        
        return cart;
    }

    public Cart getCart(String id) {
        return carts.get(id);
    }
    
    public ResponseEntity<String> addProductToCart(String cartId, Product product) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            boolean productExists = cart.getProducts().stream()
                    .anyMatch(p -> p.getId() == product.getId());
            
            if (!productExists) {
                cart.getProducts().add(product);
                return ResponseEntity.status(HttpStatus.CREATED).body("Producto agregado correctamente al carrito");
            } else {
                return ResponseEntity.badRequest().body("El producto ya existe en el carrito");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public void deleteCart(String id) {
        carts.remove(id);
    }
}