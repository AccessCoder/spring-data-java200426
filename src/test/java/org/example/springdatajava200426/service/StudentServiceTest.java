package org.example.springdatajava200426.service;

import org.example.springdatajava200426.exceptions.TaxIdNotFoundException;
import org.example.springdatajava200426.model.Student;
import org.example.springdatajava200426.model.StudentDTO;
import org.example.springdatajava200426.repository.StudentRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentRepo mockRepo = mock(StudentRepo.class);
    private IdService mockIdService = mock(IdService.class);

    @Test
    void findAll() {
        //GIVEN
        Student student = new Student("1", "123", "Max", 25);
        List<Student> expected = new ArrayList<>(List.of(student));
        StudentService testService = new StudentService(mockRepo, mockIdService);
        when(mockRepo.findAll()).thenReturn(List.of(student));
        //WHEN
        List<Student> actual = testService.findAll();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void save() {
        //GIVEN
        StudentDTO newStudent = new StudentDTO("Max", 25);
        Student expected = new Student("1", "123", "Max", 25);
        StudentService testService = new StudentService(mockRepo, mockIdService);
        when(mockIdService.generateId()).thenReturn("1");
        //WHEN
        Student actual = testService.save(newStudent);
        //THEN
        assertEquals(expected, actual);
        verify(mockRepo).save(expected);
    }

    @Test
    void findStudentByTaxId() throws TaxIdNotFoundException {

        //GIVEN
        Student expected = new Student("1", "123", "Max", 25);
        StudentService testService = new StudentService(mockRepo, mockIdService);
        when(mockRepo.findStudentByTaxId("123")).thenReturn(Optional.of(expected));
        //WHEN
        Student actual = testService.findStudentByTaxId("123");
        //THEN
        assertEquals(expected, actual);
    }
}