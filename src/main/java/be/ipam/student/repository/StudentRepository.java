package be.ipam.student.repository;

import be.ipam.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    //find student by mail
    Optional<Student> findByMail(String mail);
}
