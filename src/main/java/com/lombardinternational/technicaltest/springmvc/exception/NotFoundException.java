package com.lombardinternational.technicaltest.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends Exception {

    public NotFoundException(Integer id) {
        super(String.format("Person with id %s not found", id));
    }

}
