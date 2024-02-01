package com.example.jpanext.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attending_lectures")
public class AttendingLectures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Setter
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Setter
    private Integer midTermScore;
    @Setter
    private Integer finalsScore;


}
