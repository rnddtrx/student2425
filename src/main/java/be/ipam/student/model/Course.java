package be.ipam.student.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;
    private String courseName;
    private String courseDescription;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private Set<Enrollment> enrollments;
}
