package com.example.jpanext.school.dto;

import com.example.jpanext.school.entity.Instructor;

public interface ILCountProjection {
    /*
    private Instructor instructor;
    private Long lectureCount;
    */

    Instructor getInstructor();
    Long getLectureCount();
}
