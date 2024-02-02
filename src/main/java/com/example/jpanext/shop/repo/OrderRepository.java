package com.example.jpanext.shop.repo;

import com.example.jpanext.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
