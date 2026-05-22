package org.example.springdatajava200426.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Students")
public record Student(String id,
                      String taxId,
                      String name,
                      int age) {
}
