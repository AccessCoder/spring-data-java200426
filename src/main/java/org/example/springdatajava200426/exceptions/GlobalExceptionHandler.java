package org.example.springdatajava200426.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaxIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTaxIdNotFoundException(TaxIdNotFoundException ex){
        return "GLOBAL";
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleException(Exception ex){
//        return "Oops";
//    }
}
