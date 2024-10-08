package be.ipam.student.controller;

import be.ipam.student.dto.StudentDto;
import be.ipam.student.dto.StudentFullDto;
import be.ipam.student.model.Student;
import be.ipam.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //CRUD operations

    //Create student
    @PostMapping()
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentDto student = studentService.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        boolean isDeleted = studentService.deleteStudent(id);
        return isDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    //update student
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable int id, @RequestBody StudentDto studentDto){
        StudentDto student = studentService.updatePartialStudent(id, studentDto).orElse(null);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.notFound().build();
    }

    //read student by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable int id){
        StudentDto student = studentService.getStudentById(id).orElse(null);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.notFound().build();
    }

    //read full student by id
    @GetMapping("/full/{id}")
    public ResponseEntity<StudentFullDto> findFullStudentById(@PathVariable int id){
        StudentFullDto student = studentService.getFullStudentById(id).orElse(null);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.notFound().build();
    }
}
