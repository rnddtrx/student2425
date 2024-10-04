package be.ipam.student.mapper;

import be.ipam.student.dto.CourseFullDto;
import be.ipam.student.model.Course;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseFullMapper {
    Course toEntity(CourseFullDto courseFullDto);

    @AfterMapping
    default void linkEnrollments(@MappingTarget Course course) {
        course.getEnrollments().forEach(enrollment -> enrollment.setCourse(course));
    }

    CourseFullDto toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate(CourseFullDto courseFullDto, @MappingTarget Course course);
}