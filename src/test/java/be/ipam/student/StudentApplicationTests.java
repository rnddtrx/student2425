package be.ipam.student;

import be.ipam.student.model.Address;
import be.ipam.student.model.Enrollment;
import be.ipam.student.model.Student;
import be.ipam.student.model.Course;
import be.ipam.student.repository.CourseRepository;
import be.ipam.student.repository.EnrollmentRepository;
import be.ipam.student.repository.StudentRepository;
import be.ipam.student.service.CourseService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;

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

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    @Transactional
    void testWalkingStudent() {
        Student student = new Student();
        student.setFirstName("Luigi");
        student.setLastName("Bros");
        student.setMail("luigi7@nitendo.be");
        student = studentRepository.save(student);

        Course course = new Course();
        course.setCourseName("C++");
        course.setCourseDescription("C++ programming");
        course = courseRepository.save(course);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.saveAndFlush(enrollment);

        // JPA ne met pas à jour l'objet et utilise la version en cache
        // où l'enrollment n'est pas setté
        // on utilise donc le entityManager pour rafraichir l'objet
        // il faut rajouter le @PersistenceContext ligne 38
        entityManager.refresh(student);

        student = studentRepository.findById(student.getStudentID()).get();

        Set<Enrollment> enrollments = student.getEnrollments();
        System.out.println(student.getFirstName() + " " + student.getLastName() + " est inscrit à ");

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
    @Transactional
    void testReadCourse() {
    Student student = studentRepository.findById(2009).get();
    student.getEnrollments().forEach(enrollment -> {
        System.out.println(enrollment.getCourse().getCourseName());
    });

    }

    @Test
    void testAddStudentWithAddress() {
        Student student = new Student();
        student.setFirstName("Zelda");
        student.setLastName("TheLegend");
        student.setMail("zela2@nintendo.com");
        Address address = new Address();
        address.setCity("Hyrule");
        address.setNumber("2");
        address.setPostalCode("1234");
        address.setStreet("Castle");
        student.setAddress(address);
        address.setStudent(student);
        student = studentRepository.save(student);
        studentRepository.findById(student.getStudentID());
        assertEquals("Hyrule", student.getAddress().getCity());
    }

}
