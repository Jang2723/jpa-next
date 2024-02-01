package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository
        extends JpaRepository<Lecture, Long> {
}
