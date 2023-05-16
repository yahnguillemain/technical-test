package com.lombardinternational.technicaltest.springdata.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {

    private Long id;
    private String firstname;
    private String lastname;
    private String jobTitle;

    private LocalDate contractStartDate;

    private LocalDate contractEndDate;

    
}
