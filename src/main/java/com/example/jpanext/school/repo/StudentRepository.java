package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
}
