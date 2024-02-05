package com.example.jpanext.shop.repo;

import com.example.jpanext.shop.entity.Item;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ) // 읽기만 가능한 비관적 락
    @Query("SELECT i FROM Item i WHERE i.id = :id")
    Optional<Item> findItemForShare(
            @Param("id") Long id
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM Item i WHERE i.id = :id")
    Optional<Item> findItemForUpdate(
            @Param("id") Long id
    );

//    @Override
//    @NonNull
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    Optional<Item> findById(@NonNull Long id);

}
