package be.ipam.student.dto;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.student.model.Role}
 */
@Value
public class RoleDto implements Serializable {
    Integer id;
    @Size(max = 50)
    String roleName;
}