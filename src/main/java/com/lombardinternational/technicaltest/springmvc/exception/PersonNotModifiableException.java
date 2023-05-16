package com.lombardinternational.technicaltest.springmvc.exception;

public class PersonNotModifiableException extends Exception {

    public PersonNotModifiableException(Integer id) {
        super(String.format("Person with id %s is not modifiable", id));
    }

}
