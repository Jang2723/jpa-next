package com.example.jpanext.shop.repo;

import com.example.jpanext.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
