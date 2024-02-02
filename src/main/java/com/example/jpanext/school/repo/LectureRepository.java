package com.example.jpanext.school.repo;

import com.example.jpanext.school.dto.LectureStudentCount;
import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
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

    /*
    SELECT * FROM lecture WHERE day in ('mon','tue','wed')
    * */
    @Query("SELECT l FROM Lecture l WHERE l.day IN :days")
    List<Lecture> findByDayIn(
            @Param("days") Collection<String> days
    );


    @Query("SELECT l FROM Lecture l WHERE l.startTime <12")
    Page<Lecture> findLecturesBeforeLunch(Pageable pageable);

    @Query("SELECT l FROM Lecture l WHERE l.startTime < 12")
    List<Lecture> findLecturesBeforeLunch(Sort sort);

    @Query(
            value ="SELECT * FROM lecture WHERE start_time < 12",
            countQuery = "SELECT COUNT(*) FROM lecture WHERE start_time < 12",
            nativeQuery = true
    )
    Page<Lecture> findLecturesBeforeLunchNative(Pageable pageable);

/*    // 직접 전달할 쿼리를 작성하기 때문에 '
    // JpaRepository<T, ID>'의 T와 무관하게 사용 가능하나...
    // 별로 좋은 권장되는 방식은 아니다.
    @Query("SELECT i FROM Instructor i")
    List<Instructor> findInstructorsInLectureRepository();*/

    @Query("SELECT l FROM Lecture l " +
            "WHERE l.endTime - l.startTime > 3 ")
    List<Lecture> toLongLectures();

    // UPDATE, DELETE, INSERT의 경우 Modifying이 들어가야 한다.
    @Modifying
    @Query("UPDATE Lecture l " +
            "SET l.endTime = l.startTime + 3 " +
            "WHERE l.endTime - l.startTime > 3")
    Integer setLectureMaxHour3();

    // INSERT는 JPQL로 할 수 없다...
    @Modifying
    @Query(value =
            "INSERT INTO lecture(name, start_time, end_time, instructor_id, day) " +
            "VALUES (:name, :startTime, :endTime, :instructorId, :day)",
            nativeQuery = true
    )
    void insertLecture(
            @Param("name") String name,
            @Param("startTime") Integer startTime,
            @Param("endTime") Integer endTime,
            @Param("instructorId") Long instructorId,
            @Param("day") String day
    );

    // 강의 Lecture와 해당 Lecture를 듣는 학생 수의 리스트
//    @Query("SELECT l, SIZE(l.students) FROM Lecture  l")
//    List<Object[]> selectWithStudentCount();

    @Query("SELECT l AS lecture, SIZE(l.students) AS studentCount "  +
            "FROM Lecture l ")
    List<LectureStudentCount> selectWithStudentCount();



}
