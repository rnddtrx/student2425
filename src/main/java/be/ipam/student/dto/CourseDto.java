package be.ipam.student.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.student.model.Course}
 */
@Value
public class CourseDto implements Serializable {
    int courseID;
    String courseName;
    String courseDescription;
}