package com.project.service;


import com.project.model.Projekt;
import com.project.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentService {
    Optional<Student> getStudent(Integer studentId);
    Student setStudent(Student student);
    void deleteStudent(Integer studentId);
    Page<Projekt> getStudenci(Pageable pageable);
    Page<Projekt> searchByNazwisko(String nazwisko, Pageable pageable);
    Optional<Student> searchByIndex(String index);
}
