package com.james.ecommercestore.repository;

import com.james.ecommercestore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
