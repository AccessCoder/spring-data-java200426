package org.example.springdatajava200426.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Students")
@Builder
public record Student(String id,
                      String taxId,
                      String name,
                      int age) {
}
