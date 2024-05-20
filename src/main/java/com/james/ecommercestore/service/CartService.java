package com.james.ecommercestore.service;

import com.james.ecommercestore.model.Cart;
import com.james.ecommercestore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public ResponseEntity<Cart> getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        return ResponseEntity.ok(cart);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public ResponseEntity<Cart> updateCart(Long id, Cart cartDetails) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.setUpdatedAt(cartDetails.getUpdatedAt());
            final Cart updatedCart = cartRepository.save(cart);
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteCart(Long id) {
        cartRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
