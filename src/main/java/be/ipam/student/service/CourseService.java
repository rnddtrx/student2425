package be.ipam.student.service;

import be.ipam.student.dto.CourseDto;
import be.ipam.student.dto.CourseFullDto;
import be.ipam.student.mapper.CourseFullMapper;
import be.ipam.student.model.Course;
import be.ipam.student.mapper.CourseMapper;
import be.ipam.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseFullMapper courseFullMapper;

    public Optional<CourseDto> getCourseById(int id){
        Optional<Course> course = courseRepository.findById(id);
        // convert Course to CourseDto using mapper CourseMapper (Mapstruct)
        return course.map(courseMapper::toDto);
    }

    // get course with CourseFullDto
    public Optional<CourseFullDto> getCourseByIdFull(int id){
        Optional<Course> course = courseRepository.findById(id);
        // convert Course to CourseDto using mapper CourseMapper (Mapstruct)
        return course.map(courseFullMapper::toDto);
    }

    public Optional<Course> getCourseByName(String courseName){
        return courseRepository.findByCourseName(courseName);
    }

    public CourseDto createCourse(CourseDto courseDto) {
        Course course = courseMapper.toEntity(courseDto);
        course = courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    //Full update (PUT)
    public Optional<CourseDto> updateCourse(int id, CourseDto courseDto) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            Course courseToUpdate = courseMapper.toEntity(courseDto);
            courseToUpdate = courseRepository.save(courseToUpdate);
            return Optional.of(courseMapper.toDto(courseToUpdate));
        }
        return Optional.empty();
    }

    //Partial update (PATCH)
    public Optional<CourseDto> updatePartialCourse(int id, CourseDto courseDto) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            if (courseDto.getCourseID() != id) {
                throw new IllegalArgumentException("Cannot change the course ID.");
            }
            Course courseToUpdate = course.get();
            courseToUpdate = courseMapper.partialUpdate(courseDto, courseToUpdate);
            courseToUpdate = courseRepository.save(courseToUpdate);
            return Optional.of(courseMapper.toDto(courseToUpdate));
        }
        return Optional.empty();
    }

    public Boolean deleteCourse(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            courseRepository.delete(course.get());
            return true;
        }
        return false;
    }
}
