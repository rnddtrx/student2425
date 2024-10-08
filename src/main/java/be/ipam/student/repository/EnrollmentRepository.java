package be.ipam.student.repository;

import be.ipam.student.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    // Find all enrollments by student ID
    @Query("SELECT e FROM Enrollment e WHERE e.student.studentID = :studentID")
    Set<Enrollment> findByStudentId(int studentID);

    // Find all enrollments by course ID
    @Query("SELECT e FROM Enrollment e WHERE e.course.courseID = :courseID")
    Set<Enrollment> findByCourseId(int courseID);

}
