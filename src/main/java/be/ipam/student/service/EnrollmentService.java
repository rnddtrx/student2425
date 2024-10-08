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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    //Create enrollment
    @Transactional
    public Optional<EnrollmentDto> createEnrollment(EnrollmentDto enrollmentDto) {
        if (studentService.getStudentById(enrollmentDto.getStudent().getStudentID()).isPresent() && courseService.getCourseById(enrollmentDto.getCourse().getCourseID()).isPresent()) {
            Enrollment enrollment = enrollmentRepository.save(enrollmentMapper.toEntity(enrollmentDto));
            entityManager.refresh(enrollment);
            return Optional.of(enrollmentMapper.toDto(enrollment));
        }
        return Optional.empty();
    }

    //Update enrollment
    @Transactional
    public Optional<EnrollmentDto> updateEnrollment(int id, EnrollmentDto enrollmentDto) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        if (enrollment.isPresent()) {
            Enrollment enrollmentToUpdate = enrollmentMapper.toEntity(enrollmentDto);
            enrollmentToUpdate.setEnrollmentID(id);
            enrollmentToUpdate = enrollmentRepository.save(enrollmentToUpdate);
            entityManager.refresh(enrollmentToUpdate);
            return Optional.of(enrollmentMapper.toDto(enrollmentToUpdate));
        }
        return Optional.empty();
    }

    //Delete enrollment
    public boolean deleteEnrollment(int id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        if (enrollment.isPresent()) {
            enrollmentRepository.delete(enrollment.get());
            return true;
        }
        return false;
    }

    //Get list of enrollments by student id
    public Set<EnrollmentDto> getEnrollmentsByStudentId(int studentID) {
        return enrollmentRepository.findByStudentId(studentID).stream().map(enrollmentMapper::toDto).collect(Collectors.toSet());
    }

    //Get list of enrollments by course id
    public Set<EnrollmentDto> getEnrollmentsByCourseId(int courseID) {
        return enrollmentRepository.findByCourseId(courseID).stream().map(enrollmentMapper::toDto).collect(Collectors.toSet());
    }
}