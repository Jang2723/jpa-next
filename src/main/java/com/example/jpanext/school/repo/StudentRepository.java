package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    List<Student> findAllByAdvisorId(Long id);
    List<Student> findAllByAdvisor(Instructor instructor);

    // 지도교수가 null인 학생을 반환
    @Query("SELECT s FROM Student s " +
            "WHERE s.advisor IS NULL ")
    List<Student> noAdvisorStudent();

    @Modifying
    @Query("UPDATE Student s " +
            "SET s.advisor = :advisor " +
            "WHERE s.advisor IS NULL")
    Integer setAdvisorForStudent(
            @Param("advisor") Instructor instructor
    );

    @Query("SELECT s FROM Student s WHERE s.advisor = :advisor")
    List<Student> findByAdvisor(
            @Param("advisor")
            Instructor instructor
    );

    @Query("SELECT s FROM Student s JOIN s.advisor")
    List<Student> findAllJoin();

    @Query("SELECT s FROM Student s LEFT JOIN s.advisor")
    List<Student> findAllLeftJoin();

    @Query("SELECT s FROM Student s RIGHT JOIN s.advisor")
    List<Student> findAllRightJoin();

    @Query("SELECT s FROM Student s JOIN s.advisor WHERE s.advisor.name = :name")
    List<Student> findByAdvisorName(@Param("name") String name);

    @Query("SELECT s FROM Student s JOIN FETCH s.advisor")
    List<Student> findAllFetchAdvisor();
}

