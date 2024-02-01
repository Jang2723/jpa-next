package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;

    // cascade : 영속성 전이
    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL)
    private List<Student> advisingStudents = new ArrayList<>();
}

