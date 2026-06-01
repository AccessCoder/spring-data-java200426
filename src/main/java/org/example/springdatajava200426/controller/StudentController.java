package org.example.springdatajava200426.controller;

import org.example.springdatajava200426.exceptions.TaxIdNotFoundException;
import org.example.springdatajava200426.model.Student;
import org.example.springdatajava200426.model.StudentDTO;
import org.example.springdatajava200426.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


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
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody StudentDTO newStudent){
        return studentService.save(newStudent);
    }

    @GetMapping("/{taxId}")
    public Student getStudentByTaxId(@PathVariable
                                         String taxId) throws TaxIdNotFoundException {
        return studentService.findStudentByTaxId(taxId);
    }

    @ExceptionHandler(TaxIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTaxIdNotFoundException(TaxIdNotFoundException ex){
        return ex.getMessage();
    }
}
