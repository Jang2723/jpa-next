package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.AttendingLectures;
import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendingLectureRepository extends JpaRepository<AttendingLectures, Long> {
    List<AttendingLectures> findByStudent(Student student);
    List<AttendingLectures> findByLecture(Lecture lecture);
}
