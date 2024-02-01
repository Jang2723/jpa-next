package com.example.jpanext.school.dto;


import com.example.jpanext.school.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ILCountDto {
    private Instructor instructor;
    private Long count;
}
