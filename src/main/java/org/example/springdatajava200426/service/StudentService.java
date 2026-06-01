package org.example.springdatajava200426.service;


import org.example.springdatajava200426.exceptions.TaxIdNotFoundException;
import org.example.springdatajava200426.model.Student;
import org.example.springdatajava200426.model.StudentDTO;
import org.example.springdatajava200426.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final IdService idService;


    public StudentService(StudentRepo studentRepo, IdService idService) {
        this.studentRepo = studentRepo;
        this.idService = idService;
    }

    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    public Student save(StudentDTO newStudent) {
        Student student = Student.builder()
                .id(idService.generateId())
                .taxId("125")
                .name(newStudent.name())
                .age(newStudent.age())
                .build();
        studentRepo.save(student);
        return student;
    }

    public Student findStudentByTaxId(String taxId) throws TaxIdNotFoundException {
        return studentRepo.findStudentByTaxId(taxId)
                .orElseThrow(() -> new TaxIdNotFoundException("User with Tax Id: " + taxId + " not found!"));
    }
}
