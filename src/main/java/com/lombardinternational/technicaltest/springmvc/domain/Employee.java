package com.lombardinternational.technicaltest.springmvc.domain;

import lombok.Data;

import java.util.List;

@Data
public class Employee {

    private String identifier;
    private List<String> surnames;
    private String lastname;
    private Integer age;
    private String department;
}
