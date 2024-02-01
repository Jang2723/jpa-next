package com.example.jpanext.school;

import com.example.jpanext.school.entity.AttendingLectures;
import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import com.example.jpanext.school.repo.AttendingLecureRepository;
import com.example.jpanext.school.repo.LectureRepository;
import com.example.jpanext.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("school")
@RequiredArgsConstructor
public class SchoolController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;
    private final AttendingLecureRepository attendingLecureRepo;

    @GetMapping("many-to-many")
    public String test(){
        Student alex = Student.builder()
                .name("alex")
                .build();
        alex = studentRepository.save(alex);
        Student brad = Student.builder()
                .name("brad")
                .build();
        brad = studentRepository.save(brad);

        Lecture jpa = Lecture.builder()
                .name("jpa")
                .startTime(9)
                .endTime(16)
                .build();
        jpa = lectureRepository.save(jpa);
        Lecture spring = Lecture.builder()
                .name("spring-boot")
                .startTime(9)
                .endTime(16)
                .build();
        spring = lectureRepository.save(spring);

        alex.getAttending().add(jpa);
        alex.getAttending().add(spring);
//        spring.getStudents().add(alex);
//        spring.getStudents().add(brad);
        brad.getAttending().add(spring);
        studentRepository.save(alex);
        studentRepository.save(brad);
        lectureRepository.save(spring);
        return "done";
    }

    @GetMapping("many-to-many-get")
    public String manyToManyGet() {
        Student alex = studentRepository.findById(1L)
                .get();
        for (Lecture lecture: alex.getAttending()) {
            log.info("{} listens {}", alex.getName(), lecture.getName());
        }
        Lecture spring = lectureRepository.findById(2L)
                .get();
        for (Student student: spring.getStudents()) {
            log.info("{} listens {}", student.getName(), spring.getName());
        }

        for (AttendingLectures attendingLectures: alex.getAttendingLectures()){
            attendingLectures.setMidTermScore(80);
            attendingLectures.setFinalsScore(80);
            attendingLecureRepo.save(attendingLectures);
        }
        return "done";
    }
}
