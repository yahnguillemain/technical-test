package com.lombardinternational.technicaltest.springdata.service;

import com.lombardinternational.technicaltest.springdata.model.Department;
import com.lombardinternational.technicaltest.springdata.model.DepartmentReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentRepositoryService {
    //TODO implement data access

    public List<Department> findAll() {
        //TODO
        return null;
    }

    public Department create(Department dpt) {
        //TODO
        return null;
    }

    public Department update(Department dpt) {
        //TODO
        return null;
    }

    public Optional<Department> findById(Long id) {
        //TODO
        return null;
    }

    public void deleteById(Long id) {
        //TODO
    }

    public long count() {
        //TODO
        return -1;
    }

    public List<DepartmentReport> getDepartmentsReportAtCurrentDate() {
        //TODO
        return null;
    }
}
