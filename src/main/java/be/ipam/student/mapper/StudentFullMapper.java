package be.ipam.student.mapper;

import be.ipam.student.dto.StudentFullDto;
import be.ipam.student.model.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentFullMapper {
    Student toEntity(StudentFullDto studentFullDto);

    @AfterMapping
    default void linkEnrollments(@MappingTarget Student student) {
        student.getEnrollments().forEach(enrollment -> enrollment.setStudent(student));
    }

    StudentFullDto toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentFullDto studentFullDto, @MappingTarget Student student);
}