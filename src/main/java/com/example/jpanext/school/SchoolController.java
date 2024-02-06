package com.example.jpanext.school;


import com.example.jpanext.school.dto.ILCountDto;
import com.example.jpanext.school.dto.ILCountProjection;
import com.example.jpanext.school.entity.AttendingLectures;
import com.example.jpanext.school.entity.Instructor;
import com.example.jpanext.school.entity.Lecture;
import com.example.jpanext.school.entity.Student;
import com.example.jpanext.school.repo.AttendingLectureRepository;
import com.example.jpanext.school.repo.InstructorRepository;
import com.example.jpanext.school.repo.LectureRepository;
import com.example.jpanext.school.repo.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.generic.InstructionConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("school")
@RequiredArgsConstructor
public class SchoolController {
//    private static final Logger log
//            = LoggerFactory.getLogger(SchoolController.class);

    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;
    private final AttendingLectureRepository attendingLectureRepo;
    private final InstructorRepository instructorRepository;

    @GetMapping("pageable")
    public String pageable() {
        // 아래는 성능 문제 발생
/*        instructorPage
                = instructorRepository.findFetchPage(PageRequest.of(0,5));*/
        // LAZY 를 하면 데이터가 필요할떄까지는 추가 쿼리가 필요없다.
        Page<Instructor> instructorPage
                = instructorRepository.findAll(PageRequest.of(0,5));

        // 실제 데이터를 가져올 때 쿼리가 발생한다.
        for (Instructor instructor : instructorPage){
            instructor.getAdvisingStudents().size();
        }
        return "done";
    }

    @GetMapping("multi-bag") // 오류 나는게 정상
    public String multiBag() {
//        List<Instructor> instructors
//                = instructorRepository.findFetchStudentAndLecture();
        List<Instructor> instructors
                = instructorRepository.findWithStudentAndLecture();

        for (Instructor instructor: instructors) {
            log.info("{}", instructor.getAdvisingStudents().size());
            log.info("{}", instructor.getLectures().size());
        }
        return "done";
    }

    @GetMapping("entity-graph")
    public String entityGraph(){
        List<Instructor> instructors = instructorRepository.findByEntityGraph();
        for (Instructor instructor: instructors){
            log.info("{}",instructor.getAdvisingStudents().size());
        }
        return "done";
    }

    @GetMapping("fetch-join")
    public String fetchJoin(){
//        List<Student> students = studentRepository.findAllFetchAdvisor();
//        for(Student student: students){
//            student.getAdvisor().getName();
//        }
        List<Instructor> instructors
                = instructorRepository.findAllFetchStudents();
        for (Instructor instructor: instructors){
            log.info("{}",instructor.getAdvisingStudents().size());
        }
        return "done";
    }

    @GetMapping("join")
    public String join() {
        log.info("{}", studentRepository.findAllJoin().size());
        log.info("{}", studentRepository.findAllLeftJoin().size());
        log.info("{}", studentRepository.findAllRightJoin().size());
        log.info("{}", studentRepository.findByAdvisorName("s"));
        
//        for (Student student: studentRepository.findAllJoin()){
//            student.getAdvisor().getName();
//        }

        return "done";
    }

    @GetMapping("fetch-type")
    public String fetchType(){
//        studentRepository.findAll();
//        List<Instructor> instructors = instructorRepository.findAll();
//        for (Instructor instructor : instructors){
////            log.info("advising student count: {}"
////                    , instructor.getAdvisingStudents().size());
//            // PersistentBag
//            log.info("{}", instructor.getAdvisingStudents().getClass());
//        }
        List<Student> students = studentRepository.findAll();
        for(Student student: students) {
            if (student.getAdvisor() != null) {
                log.info("{}", student.getAdvisor().getClass());
                log.info("{}", student.getAdvisor().getId());
            }
        }

        // SELECT t FROM T t;
        instructorRepository.findAll();
        studentRepository.findAll();
        return "done";
    }

    @GetMapping(value = "test-agg")
    public String testAggregate() {
        List<Object[]> results
                = instructorRepository.selectILCountObject();
        for (Object[] row: results){
            Instructor instructor = (Instructor) row[0];
            // log.info(String.valueOf(row[1].getClass()));
            Long count = (Long) row[1];
            log.info("{}: {}",instructor.getName(),count );
        }
        
        List<ILCountDto> resultDtos = 
                instructorRepository.selectILCountDto();
        for (ILCountDto dto: resultDtos) {
            log.info("{}: {}",
                    dto.getInstructor().getName(),
                    dto.getCount());
        }

        List<ILCountProjection> resultlProjs =
                instructorRepository.selectILCountProj();
        for(ILCountProjection projection: resultlProjs){
            log.info("{}: {}",
                    projection.getInstructor().getName(),
                    projection.getLectureCount());
        }

        lectureRepository.selectWithStudentCount()
                .forEach(projection ->
                        log.info("{}: {}",
                                projection.getLecture().getName(),
                                projection.getStudentCount()));
//                .forEach(objects ->
//                        log.info("{}: {}", objects));

        return "done";
    }

    @GetMapping("test-query")
    public String testQuery() {
        /*List<Lecture> lectures = lectureRepository.findLecturesBeforeLaunch();
        lectures = lectureRepository.findLecturesBeforeLunchNative();
        for (Lecture lecture: lectures){
            log.info("{}: {}" , lecture.getId(), lecture.getStartTime());
        }
*/
        log.info("JPQL Sample");
        lectureRepository.findLecturesBeforeLunch().forEach(lecture ->
                log.info("{}: {}", lecture.getName(), lecture.getStartTime()));
        lectureRepository.findLecturesBeforeLunchNative().forEach(lecture ->
                log.info("{}: {}", lecture.getName(), lecture.getStartTime()));

        log.info("=================== indexed parameters");
        lectureRepository.findLecturesByTime(10, 13).forEach(lecture ->
                log.info("{}: {} -> {}",
                        lecture.getName(),
                        lecture.getStartTime(),
                        lecture.getEndTime()));

        lectureRepository.findLecturesByTimeNative(10, 15).forEach(lecture ->
                log.info("{}: {} -> {}",
                        lecture.getName(),
                        lecture.getStartTime(),
                        lecture.getEndTime()));

        log.info("=================== named parameters");
        lectureRepository.findLecturesByTimeNamed(10, 13).forEach(lecture ->
                log.info("{}: {} -> {}",
                        lecture.getName(),
                        lecture.getStartTime(),
                        lecture.getEndTime()));

        lectureRepository.findByDayIn(Set.of("mon","tue")).forEach(lecture ->
                log.info("{}: {}",
                        lecture.getName(),
                        lecture.getDay()));

        Page<Lecture> lecturePage =
                lectureRepository.findAll(PageRequest.of(0,10));

        lecturePage = lectureRepository.findLecturesBeforeLunch(
                PageRequest.of(0,4));
        lecturePage.stream().forEach(lecture ->
                log.info("{}: {}", lecture.getName(), lecture.getStartTime()));

        lectureRepository.findLecturesBeforeLunch(
                Sort.by(Sort.Direction.DESC, "id")).forEach(lecture ->
                log.info("{}: {}", lecture.getId(), lecture.getStartTime()));

        lectureRepository.findLecturesBeforeLunchNative(
                PageRequest.of(0,4)).forEach(lecture ->
                log.info("{}: {}", lecture.getId(), lecture.getStartTime()));

        // 서로 다른 Repository에서 무관한 Entity를 조회하는 행위를 지양할 것
        /* lectureRepository.findInstructorsInLectureRepository().forEach(instructor ->
                log.info("{}", instructor.getId()));*/

        return "done";
    }

    @Transactional
    @GetMapping("test-modifying")
    public String modifying() {
        log.info("modifying");
        lectureRepository.toLongLectures().forEach(lecture ->
                log.info("{}: {}",
                        lecture.getName(),
                        lecture.getEndTime() - lecture.getStartTime()));
        lectureRepository.setLectureMaxHour3();
        log.info("lectures over 3 hours: {}", lectureRepository.toLongLectures().size());

        studentRepository.noAdvisorStudent().forEach(student ->
                log.info("{}: {}", student.getId(), student.getName()));

        Instructor instructor = instructorRepository.findById(1L).get();
        log.info("row affected: {}",
                studentRepository.setAdvisorForStudent(instructor));

        studentRepository.noAdvisorStudent().forEach(student ->
                log.info("{}: {}", student.getId(), student.getName()));

        return "done";
    }

    // CascadeType.PERSIST일때만 전부 저장됨
    @GetMapping("cascade-persist")
    public String cascadePersist() {
        // 강사를 만들고,
        Instructor instructor = Instructor.builder()
                .name("Isaac Newton")
                .build();

        // 여러 학생을 만들고,
        Student alex = Student.builder()
                .name("Alex")
                .advisor(instructor)
                .build();

        Student brad = Student.builder()
                .name("Brad")
                .advisor(instructor)
                .build();

        // 강사의 지도학생으로 등록한다.
        instructor.getAdvisingStudents().add(alex);
        instructor.getAdvisingStudents().add(brad);
//        instructor.getAdvisingStudents().addAll(List.of(alex, brad));
        instructorRepository.save(instructor);
        return "done";
    }

    @GetMapping("one-to-many-temp") // 학생을 저장해야 강사가 저장되는 과정을 보여줌
    public String oneToManyTemp() {
        // 강사를 만들고,
        Instructor instructor = Instructor.builder()
                .name("Isaac Newton")
                .build();
        // 강사를 저장
        instructor = instructorRepository.save(instructor);

        // 여러 학생을 만들고,
        Student alex = Student.builder()
                .name("Alex")
                .advisor(instructor)
                .build();

        Student brad = Student.builder()
                .name("Brad")
                .advisor(instructor)
                .build();

        // 학생을 저장한다.
        studentRepository.save(alex);
        studentRepository.save(brad);
        return "done";
    }

    // CascadeType.REMOVE일때 전체 삭제됨
    @GetMapping("cascade-remove")
    public String cascadeRemove() {
        instructorRepository.deleteById(1L);
        return "done";
    }

    @GetMapping("many-to-many")
    public String manyToMany() {
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
                .name("spring boot")
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

        for (AttendingLectures attendingLecture: alex.getAttendingLectures()) {
            attendingLecture.setMidTermScore(80);
            attendingLecture.setFinalsScore(80);
            attendingLectureRepo.save(attendingLecture);
        }

        return "done";
    }

}