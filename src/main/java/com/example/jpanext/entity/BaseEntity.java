package com.example.jpanext.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 다른 Entity가 상속받아서 내가 가진 속성 정보를 포함시키고 싶을 때
// 상속 받는 Entity 클래스 임을 나타내는 Annotation
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createAt;
    @CreatedDate
    private LocalDateTime updateAt;
}
