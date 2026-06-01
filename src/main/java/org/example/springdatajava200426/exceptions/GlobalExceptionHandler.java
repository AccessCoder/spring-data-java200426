package org.example.springdatajava200426.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                            HttpHeaders headers,
                                                                            HttpStatusCode status,
                                                                            WebRequest request) {
        //Hier speichern wir alle fehlerhaften Felder + Fehlermeldungen ab
        //Und returnen es später als JSON
        Map<String, String> validationErrors = new HashMap<>();

        //Alle Validierungsfehler aus der Exception!
        List<FieldError> allErrors = ex.getBindingResult().getFieldErrors();

        allErrors.forEach(error -> {
            //ObjectError zu Fielderror casten, damit wir den Feldnamen auslesen können
            String fieldName = error.getField();
            //Fehlermeldung auslesen
            String errorMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TaxIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleTaxIdNotFoundException(TaxIdNotFoundException ex, WebRequest request) {
        ErrorResponseDTO temp = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorMsg(ex.getMessage())
                .errorCode(HttpStatus.NOT_FOUND)
                .errorTime(LocalDateTime.now())
                .build();
        return temp;
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleException(Exception ex){
//        return "Oops";
//    }
}
