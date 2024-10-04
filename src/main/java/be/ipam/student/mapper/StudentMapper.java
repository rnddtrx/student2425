package be.ipam.student.mapper;

import be.ipam.student.dto.StudentDto;
import be.ipam.student.model.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    Student toEntity(StudentDto studentDto);

    @AfterMapping
    default void linkEnrollments(@MappingTarget Student student) {
        student.getEnrollments().forEach(enrollment -> enrollment.setStudent(student));
    }

    StudentDto toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDto studentDto, @MappingTarget Student student);
}