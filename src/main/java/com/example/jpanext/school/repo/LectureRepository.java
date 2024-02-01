package com.example.jpanext.school.repo;

import com.example.jpanext.school.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository
        extends JpaRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l WHERE l.startTime < 12")
    List<Lecture> findLecturesBeforeLunch();

    @Query(
            value = "SELECT *" +
                    "FROM lecture " +
                    "WHERE start_time < 12",
            // 그냥 SQL 사용 옵션
            nativeQuery = true
    )
    List<Lecture> findLecturesBeforeLunchNative();

    @Query("SELECT l FROM Lecture l " +
            "WHERE l.startTime = ?1 AND l.endTime = ?2")
    List<Lecture> findLecturesByTime(
            Integer startTime,
            Integer endTime
    );

    @Query("SELECT l FROM Lecture l "+
    "WHERE l.startTime = :start " +
    "AND l.endTime = :end")
    List<Lecture> findLecturesByTimeNamed(
            @Param("start") Integer startTime,
            @Param("end") Integer endTime
    );

    @Query(
            value = "SELECT * FROM lecture " +
                    "WHERE start_time = ?1 AND end_time = ?2",
            nativeQuery = true
    )
    List<Lecture> findLecturesByTimeNative(
            Integer startTime,
            Integer endTime
    );
}
