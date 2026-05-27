package org.example.springdatajava200426.controller;

import org.example.springdatajava200426.model.Student;
import org.example.springdatajava200426.model.StudentDTO;
import org.example.springdatajava200426.repository.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepo studentRepo;

    @Test
    void getAllStudents_shouldReturnEmptyList_whenCalledInitially() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(get("/api/student"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void saveStudent() throws Exception {
        //GIVEN
        StudentDTO dto = new StudentDTO("Max", 25);

        ObjectMapper mapper = new ObjectMapper();

        String requestJson = mapper.writeValueAsString(dto);
        String responseJson = mapper.writeValueAsString(new Student("1", "123", "Max", 25));

        //WHEN
        mockMvc.perform(post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        //THEN
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void getStudentByTaxId() throws Exception {
        //GIVEN
        Student student = new Student("1", "123", "Max", 25);
        studentRepo.save(student);
        //WHEN
        mockMvc.perform(get("/api/student/" + student.taxId()))
        //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                              "name": "Max",
                              "age": 25,
                              "taxId": "123",
                              "id": "1"
                            }
        """));
    }
}