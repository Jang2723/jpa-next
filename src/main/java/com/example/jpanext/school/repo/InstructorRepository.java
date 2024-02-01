package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
