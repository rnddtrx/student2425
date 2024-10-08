package be.ipam.student.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link be.ipam.student.model.Enrollment}
 */
@Value
public class EnrollmentWithCourseDto implements Serializable {
    int enrollmentID;
    CourseDto course;
    Date enrollmentDate;
}