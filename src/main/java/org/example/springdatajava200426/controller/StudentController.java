package org.example.springdatajava200426.controller;

import org.example.springdatajava200426.model.Student;
import org.example.springdatajava200426.repository.StudentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }

    @GetMapping("/{taxId}")
    public Student getStudentByTaxId(@PathVariable String taxId){
        return studentRepo.findStudentByTaxId(taxId)
                .orElseThrow();
    }

}
