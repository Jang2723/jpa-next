package com.example.jpanext.shop;

import com.example.jpanext.shop.entity.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityManagerService {
    private final EntityManager entityManager;

    @Transactional
    public void save() {
        Item newItem = Item.builder()
                .name("new item")
                .build();

        entityManager.persist(newItem);
        entityManager.persist(Item.builder()
                .name("new item2")
                .build());
    }

    @Transactional
    public void find() {
        Item targetItem = entityManager.find(
                Item.class,
                1L
        );
        log.info(targetItem.getName());
        /*Item targetItem = entityManager.find(
                T,
                ID
        );*/
    }
}
