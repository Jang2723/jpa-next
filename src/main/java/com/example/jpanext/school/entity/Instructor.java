package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // OneToMany의 데이터가 필요해지면, 한번에 많은 데이터를 가져오도록
    // 이떄의 기준은 여러 Instructor를 기준으로 List<Student>를 검색
    @BatchSize(size = 5)
    // cascade : 영속성 전이
    @OneToMany(mappedBy = "advisor",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Student> advisingStudents = new ArrayList<>();
//    private final Set<Student> advisingStudents = new HashSet<>(); // 실행은 됨

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY) // Lecture가 가진 Instructor의 객체 이름(instructor)
    private final List<Lecture> lectures = new ArrayList<>();
//    private final Set<Lecture> lectures = new HashSet<>(); // 실행은 됨

}

