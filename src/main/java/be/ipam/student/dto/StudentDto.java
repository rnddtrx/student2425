package be.ipam.student.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link be.ipam.student.model.Student}
 */
@Value
public class StudentDto implements Serializable {
    int studentID;
    String firstName;
    String lastName;
    Date birthDate;
    String mail;
    String passwordHash;
    Set<RoleDto> roles;
}