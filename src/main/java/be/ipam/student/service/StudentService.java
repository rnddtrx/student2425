package be.ipam.student.service;

import be.ipam.student.model.Student;
import be.ipam.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudentById(int studentID) {
        return studentRepository.findById(studentID);
    }
}
