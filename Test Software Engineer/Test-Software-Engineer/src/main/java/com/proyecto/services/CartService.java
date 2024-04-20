package com.proyecto.services;

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

    public void addProductToCart(String cartId, Product product) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            cart.getProducts().add(product);
        }
    }

    public void deleteCart(String id) {
        carts.remove(id);
    }
}