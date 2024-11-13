package be.ipam.student.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String mail;
    private String passwordHash;
    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_Role",
            joinColumns = @JoinColumn(name = "studentID"),
            inverseJoinColumns = @JoinColumn(name = "roleID"))
    private Set<Role> roles;

}
