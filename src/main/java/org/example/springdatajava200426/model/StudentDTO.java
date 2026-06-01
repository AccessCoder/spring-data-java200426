package org.example.springdatajava200426.model;


import jakarta.validation.constraints.*;

public record StudentDTO(

        @NotBlank(message = "name can not be blank!")
        @Size(min = 2, max = 50, message = "The length of the name should be between 2 and 50")
        String name,

        @NotNull(message = "age can not be null!")
        @Min(value = 16,message = "age must be over 15")
        @Max(value = 78, message = "age must be under 79")
        Integer age) {
}
