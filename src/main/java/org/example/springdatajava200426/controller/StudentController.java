package org.example.springdatajava200426.controller;

import org.example.springdatajava200426.model.Student;
import org.example.springdatajava200426.model.StudentDTO;
import org.example.springdatajava200426.repository.StudentRepo;
import org.example.springdatajava200426.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @PostMapping
    public Student saveStudent(@RequestBody StudentDTO newStudent){
        return studentService.save(newStudent);
    }

    @GetMapping("/{taxId}")
    public Student getStudentByTaxId(@PathVariable String taxId){
        return studentService.findStudentByTaxId(taxId);
    }

}
