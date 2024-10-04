package be.ipam.student.service;

import be.ipam.student.dto.EnrollmentDto;
import be.ipam.student.model.Enrollment;
import be.ipam.student.repository.EnrollmentRepository;
import be.ipam.student.mapper.EnrollmentMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Optional<EnrollmentDto> createEnrollment(EnrollmentDto enrollmentDto) {
        if (studentService.getStudentById(enrollmentDto.getStudent().getStudentID()).isPresent() && courseService.getCourseById(enrollmentDto.getCourse().getCourseID()).isPresent()) {
            Enrollment enrollment = enrollmentService.save(enrollmentMapper.toEntity(enrollmentDto));
            entityManager.refresh(enrollment);
            return Optional.of(enrollmentMapper.toDto(enrollment));
        }
        return Optional.empty();
    }
}
