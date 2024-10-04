package be.ipam.student.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link be.ipam.student.model.Course}
 */
@Value
public class CourseFullDto implements Serializable {
    int courseID;
    String courseName;
    String courseDescription;
    Set<EnrollmentDto1> enrollments;

    /**
     * DTO for {@link be.ipam.student.model.Enrollment}
     */
    @Value
    public static class EnrollmentDto1 implements Serializable {
        int enrollmentID;
        StudentDto student;
        Date enrollmentDate;

        /**
         * DTO for {@link be.ipam.student.model.Student}
         */
        @Value
        public static class StudentDto implements Serializable {
            int studentID;
            String firstName;
            String lastName;
            Date birthDate;
            String mail;
            String passwordHash;
        }
    }
}