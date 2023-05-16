package com.lombardinternational.technicaltest.springdata.service;

import com.lombardinternational.technicaltest.springdata.model.Department;
import com.lombardinternational.technicaltest.springdata.model.Employee;
import com.lombardinternational.technicaltest.springdata.model.EmployeeSearchCriterion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeRepositoryService {
    //TODO

    public long countEmployeeOfDepartment(Department department) {
        //TODO
        return -1;
    }

    public List<Employee> findAll() {
        //TODO
        return null;
    }

    public Employee save(Employee employee) {
        //TODO
        return null;
    }

    public Optional<Employee> findById(Long integer) {
        //TODO
        return null;
    }

    public long count() {
        //TODO
        return -1;
    }

    public void deleteById(Long integer) {
        //TODO
    }

    public Page<Employee> search(List<EmployeeSearchCriterion> criteria, Pageable pageable) {

        //TODO
        return null;
    }

}
