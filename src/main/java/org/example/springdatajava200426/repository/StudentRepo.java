package org.example.springdatajava200426.repository;

import org.example.springdatajava200426.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends MongoRepository<Student,String> {

    Optional<Student> findStudentByTaxId(String taxId);
}
