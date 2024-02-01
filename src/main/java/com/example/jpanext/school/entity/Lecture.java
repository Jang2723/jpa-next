package com.example.jpanext.school.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;
    @Setter
    private String day;
    @Setter
    private Integer startTime;
    @Setter
    private Integer endTime;

    @ManyToMany(mappedBy = "attending")
    private final List<Student> students = new ArrayList<>();
}
