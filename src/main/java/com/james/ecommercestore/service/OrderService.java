package com.james.ecommercestore.service;

import com.james.ecommercestore.model.Order;
import com.james.ecommercestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public ResponseEntity<Order> getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return ResponseEntity.ok(order);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public ResponseEntity<Order> updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setTotalAmount(orderDetails.getTotalAmount());
            order.setStatus(orderDetails.getStatus());
            order.setUpdatedAt(orderDetails.getUpdatedAt());
            final Order updatedOrder = orderRepository.save(order);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteOrder(Long id) {
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
