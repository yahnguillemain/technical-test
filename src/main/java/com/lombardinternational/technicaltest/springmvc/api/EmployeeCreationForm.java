package com.lombardinternational.technicaltest.springmvc.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeCreationForm {

    private String identifier;
    private List<String> surnames;
    private String lastname;
    private Integer age;
    private String department;
}
