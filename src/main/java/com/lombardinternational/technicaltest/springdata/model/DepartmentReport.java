package com.lombardinternational.technicaltest.springdata.model;

import lombok.Data;

@Data
public class DepartmentReport extends Department{
    private long numberOfEmployees;

    public DepartmentReport(Long id, String name, long numberOfEmployees) {
        super(id,name);
        this.numberOfEmployees = numberOfEmployees;
    }
}
