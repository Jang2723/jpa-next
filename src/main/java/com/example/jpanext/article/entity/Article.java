package com.example.jpanext.article.entity;

import com.example.jpanext.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String title;
    private String content;
    private String writer;

    @OneToMany(mappedBy = "article")
    private final List<Comment> comments = new ArrayList<>();
}
