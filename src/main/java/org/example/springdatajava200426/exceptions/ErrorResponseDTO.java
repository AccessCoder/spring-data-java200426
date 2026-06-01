package org.example.springdatajava200426.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(String apiPath,
                               HttpStatus errorCode,
                               String errorMsg,
                               LocalDateTime errorTime) {
}
