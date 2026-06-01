package org.example.springdatajava200426.controller;

import org.example.springdatajava200426.exceptions.TaxIdNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fail")
public class FailController {


    @GetMapping
    public String fail() throws TaxIdNotFoundException {
        throw new TaxIdNotFoundException("FAIL!");
    }
}
