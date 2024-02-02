package com.example.jpanext.shop.repo;

import com.example.jpanext.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
