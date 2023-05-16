package com.lombardinternational.technicaltest.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSearchCriterion {

    private Type criterion;
    private Operator operator;
    private Object value;

    public enum Operator {
        EQUALS,
        CONTAINS
    }

    public enum Type {
        FIRSTNAME,
        LASTNAME,
        JOB_TITLE,
        MANAGER
    }
}