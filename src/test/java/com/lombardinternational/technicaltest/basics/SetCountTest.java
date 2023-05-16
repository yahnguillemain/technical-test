package com.lombardinternational.technicaltest.basics;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SetCountTest {



    @Test
    public void checkSetSizeCountIsOk(){
        Set<Employee> employees=new HashSet<>();
        employees.add(new Employee("Henri", "Martin","IT"));
        employees.add(new Employee("Jean", "Jacques","HR"));
        employees.add(new Employee("Edouard", "Jacques","IT"));
        employees.add(new Employee("Paul", "Martin","IT"));
        employees.add(new Employee("Helene", "Martin","HR"));
        assertThat(employees).hasSize(5);
    }
}
