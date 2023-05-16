package com.lombardinternational.technicaltest.springdata.service;

import com.lombardinternational.technicaltest.springdata.model.Employee;
import com.lombardinternational.technicaltest.springdata.model.EmployeeSearchCriterion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeRepositoryServiceTest {

    @Autowired
    private EmployeeRepositoryService service;

    @Autowired
    private DepartmentRepositoryService departmentRepositoryService;

    @Test
    public void countAllEmployees() {
        assertThat(service.count()).isEqualTo(50L);
    }

    @Test
    public void countEmployeesOfADepartment() {
        assertThat(service.countEmployeeOfDepartment(departmentRepositoryService.findById(10L).orElseThrow())).isEqualTo(5L);
        assertThat(service.countEmployeeOfDepartment(departmentRepositoryService.findById(6L).orElseThrow())).isEqualTo(6L);
    }

    @Test
    void searchByExactFirstname() {
        List<EmployeeSearchCriterion> criteria =
            Arrays.asList(new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.FIRSTNAME, EmployeeSearchCriterion.Operator.EQUALS, "Erasmus"));
        Page<Employee> result = service.search(criteria, Pageable.ofSize(10));
        assertThat(result).isNotEmpty();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.stream().findFirst()).isPresent().get().extracting(Employee::getLastname).isEqualTo("Illsley");
    }

    @Test
    void searchByExactFirstnameAndLastname() {
        List<EmployeeSearchCriterion> criteria = Arrays.asList(
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.FIRSTNAME, EmployeeSearchCriterion.Operator.EQUALS, "Erasmus"),
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.LASTNAME, EmployeeSearchCriterion.Operator.EQUALS, "Illsley")
        );
        Page<Employee> result = service.search(criteria, Pageable.ofSize(10));
        assertThat(result).isNotEmpty();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.stream().findFirst()).isPresent().get().extracting(Employee::getLastname).isEqualTo("Illsley");
    }

    @Test
    void searchByFirstnameContaining() {
        List<EmployeeSearchCriterion> criteria = Arrays.asList(
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.FIRSTNAME, EmployeeSearchCriterion.Operator.CONTAINS, "us")
        );
        Page<Employee> result = service.search(criteria, Pageable.ofSize(10));
        assertThat(result).isNotEmpty();
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.stream()).extracting(Employee::getLastname).containsExactlyInAnyOrder("Illsley", "Keers");
    }

    @Test
    void searchByManagerId() {
        List<EmployeeSearchCriterion> criteria = Arrays.asList(
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.MANAGER, EmployeeSearchCriterion.Operator.EQUALS, 3)
        );
        Page<Employee> result = service.search(criteria, Pageable.ofSize(10));
        assertThat(result).isNotEmpty();
        assertThat(result.getTotalElements()).isEqualTo(15L);
    }

    @Test
    void searchByManagerIdAnJobTitle() {
        List<EmployeeSearchCriterion> criteria = Arrays.asList(
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.MANAGER, EmployeeSearchCriterion.Operator.EQUALS, 3),
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.JOB_TITLE, EmployeeSearchCriterion.Operator.EQUALS, "Research Nurse")
        );
        Page<Employee> result = service.search(criteria, Pageable.ofSize(10));
        assertThat(result).isNotEmpty();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.stream().findFirst()).isPresent().get().extracting(Employee::getLastname).isEqualTo("Richfield");
    }

    @Test
    void searchByManagerIdAndFirstNameContainingAndLastNameContaining() {
        List<EmployeeSearchCriterion> criteria = Arrays.asList(
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.MANAGER, EmployeeSearchCriterion.Operator.EQUALS, 3),
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.FIRSTNAME, EmployeeSearchCriterion.Operator.CONTAINS, "a"),
            new EmployeeSearchCriterion(EmployeeSearchCriterion.Type.LASTNAME, EmployeeSearchCriterion.Operator.CONTAINS, "a")
        );
        Page<Employee> result = service.search(criteria, Pageable.ofSize(10));
        assertThat(result).isNotEmpty();
        assertThat(result.getTotalElements()).isEqualTo(3L);
    }

}
