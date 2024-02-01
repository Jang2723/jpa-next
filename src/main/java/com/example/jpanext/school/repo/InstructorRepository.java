package com.example.jpanext.school.repo;

import com.example.jpanext.school.dto.ILCountDto;
import com.example.jpanext.school.dto.ILCountProjection;
import com.example.jpanext.school.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository
        extends JpaRepository<Instructor, Long> {
    @Query("SELECT l.instructor, COUNT(*) FROM Lecture l " +
            "GROUP BY l.instructor")
    List<Object[]> selectILCountObject();

    @Query("SELECT new com.example.jpanext.school.dto.ILCountDto(l.instructor, COUNT(*))" +
            "FROM Lecture l " +
            "GROUP BY l.instructor")
    List<ILCountDto> selectILCountDto();

    @Query("SELECT l.instructor AS instructor, COUNT(*) as lectureCount " +
            "FROM Lecture l " +
            "GROUP BY l.instructor"
    )
    List<ILCountProjection> selectILCountProj();
}
