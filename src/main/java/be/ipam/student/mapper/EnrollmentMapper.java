package be.ipam.student.mapper;

import be.ipam.student.dto.CourseDto;
import be.ipam.student.dto.EnrollmentDto;
import be.ipam.student.dto.EnrollmentWithCourseDto;
import be.ipam.student.dto.StudentDto;
import be.ipam.student.model.Course;
import be.ipam.student.model.Enrollment;
import be.ipam.student.model.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CourseMapper.class})
public interface EnrollmentMapper {
    Enrollment toEntity(EnrollmentDto enrollmentDto);

    EnrollmentDto toDto(Enrollment enrollment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Enrollment partialUpdate(EnrollmentDto enrollmentDto, @MappingTarget Enrollment enrollment);


    Enrollment toEntity(EnrollmentWithCourseDto enrollmentWithCourseDto);

    EnrollmentWithCourseDto toDto1(Enrollment enrollment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Enrollment partialUpdate(EnrollmentWithCourseDto enrollmentWithCourseDto, @MappingTarget Enrollment enrollment);
}