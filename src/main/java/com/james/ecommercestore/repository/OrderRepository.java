package com.james.ecommercestore.repository;

import com.james.ecommercestore.model.Cart;
import com.james.ecommercestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
