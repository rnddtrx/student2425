package be.ipam.student.service;

import be.ipam.student.model.Course;
import be.ipam.student.model.Student;
import be.ipam.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course getCourseById(int id){
        return courseRepository.findById(id).get();
    }

}
