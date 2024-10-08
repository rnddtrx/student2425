package be.ipam.student.service;

import be.ipam.student.dto.StudentDto;
import be.ipam.student.dto.StudentFullDto;
import be.ipam.student.mapper.StudentFullMapper;
import be.ipam.student.mapper.StudentMapper;
import be.ipam.student.model.Student;
import be.ipam.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentFullMapper studentFullMapper;

    //Read student by id
    public Optional<StudentDto> getStudentById(int studentID) {
        return studentRepository.findById(studentID).map(studentMapper::toDto);
    }

    //Read full student by id
    public Optional<StudentFullDto> getFullStudentById(int student){
        return studentRepository.findById(student).map(studentFullMapper::toDto);
    }

    //Create student
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public boolean deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<StudentDto> updatePartialStudent(int id, StudentDto studentDto) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student studentToUpdate = studentMapper.toEntity(studentDto);
            studentToUpdate.setStudentID(id);
            studentToUpdate = studentRepository.save(studentToUpdate);
            return Optional.of(studentMapper.toDto(studentToUpdate));
        }
        return Optional.empty();
    }
}
