package com.example.jpanext.school;

import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import com.example.jpanext.school.repo.LectureRepository;
import com.example.jpanext.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("school")
@RequiredArgsConstructor
public class SchoolController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @GetMapping("many-to-many")
    public String test(){
        Student alex = Student.builder()
                .first_name("alex")
                .last_name("rod")
                .build();
        alex = studentRepository.save(alex);

        Lecture jpa = Lecture.builder()
                .name("jpa")
                .startTime(9)
                .endTime(16)
                .build();
        jpa = lectureRepository.save(jpa);

        alex.getAttending().add(jpa);
        studentRepository.save(alex);
        return "done";
    }
}
