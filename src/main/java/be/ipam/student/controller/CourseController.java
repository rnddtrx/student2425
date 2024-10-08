package be.ipam.student.controller;

import be.ipam.student.dto.CourseDto;
import be.ipam.student.dto.CourseFullDto;
import be.ipam.student.model.Course;
import be.ipam.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //CRUD operations

    //GET Read 1 course by id
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findCourseById(@PathVariable int id){
        Optional<CourseDto> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //POST Create 1 course
    @PostMapping()
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        CourseDto course = courseService.createCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    //PUT Update 1 course
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable int id, @RequestBody CourseDto courseDto){
        Optional<CourseDto> course = courseService.updateCourse(id, courseDto);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //PATCH Partial update 1 course
    @PatchMapping("/{id}")
    public ResponseEntity<CourseDto> updatePartialCourse(@PathVariable int id, @RequestBody CourseDto courseDto){
        Optional<CourseDto> course = courseService.updatePartialCourse(id, courseDto);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //DELETE Delete 1 course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id){
        boolean isDeleted = courseService.deleteCourse(id);
        return isDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    //Custom operations
    @GetMapping("/full/{id}")
    public ResponseEntity<CourseFullDto> findCourseByIdFull(@PathVariable int id){
        Optional<CourseFullDto> course = courseService.getCourseByIdFull(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{courseName}")
    public ResponseEntity<CourseDto> findCourseByName(@PathVariable String courseName){
        Optional<CourseDto> course = courseService.getCourseByName(courseName);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
