package be.ipam.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentID;
    @ManyToOne
    @JoinColumn(name = "studentID")
    @JsonIgnore
    private Student student;
    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course;
    private Date enrollmentDate;
}
