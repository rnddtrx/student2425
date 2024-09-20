package be.ipam.student;

import be.ipam.student.model.Enrollment;
import be.ipam.student.model.Student;
import be.ipam.student.model.Course;
import be.ipam.student.repository.CourseRepository;
import be.ipam.student.repository.EnrollmentRepository;
import be.ipam.student.repository.StudentRepository;
import be.ipam.student.service.CourseService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentApplicationTests {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Test
    @Transactional
    void testWalkingStudent(){
        Student student = new Student();
        student.setFirstName("Luigi");
        student.setLastName("Bros");
        student.setMail("luigi4@nitendo.be");
        student = studentRepository.save(student);

        Course course = new Course();
        course.setCourseName("C++");
        course.setCourseDescription("C++ programming");
        course = courseRepository.saveAndFlush(course);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment = enrollmentRepository.saveAndFlush(enrollment);

        student.getEnrollments().add(enrollment);
        student = studentRepository.saveAndFlush(student);

        student = studentRepository.findById(student.getStudentID()).get();

        Set<Enrollment> enrollments = student.getEnrollments();

        System.out.println(student.getFirstName() + " " + student.getLastName() + " est inscrit");
        enrollments.forEach(enroll -> {
            System.out.println(enroll.getCourse().getCourseName());
        });
    }



    @Test
    void contextLoads() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }

    @Test
    void testAddStudent() {
        Student student = new Student();
        student.setFirstName("Mario");
        student.setLastName("Bros");
        student.setMail("mario.bros@nintendo.jp");
        student = studentRepository.save(student);
        studentRepository.findById(student.getStudentID());
        assertEquals("Mario", student.getFirstName());
    }

    @Test
    void testReadCourse() {
    Student student = studentRepository.findById(2009).get();
    student.getEnrollments().forEach(enrollment -> {
        System.out.println(enrollment.getCourse().getCourseName());
    });

    }

}
