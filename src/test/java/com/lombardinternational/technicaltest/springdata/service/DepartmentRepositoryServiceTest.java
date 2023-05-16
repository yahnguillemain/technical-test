package com.lombardinternational.technicaltest.springdata.service;

import com.lombardinternational.technicaltest.springdata.model.Department;
import com.lombardinternational.technicaltest.springdata.model.DepartmentReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepartmentRepositoryServiceTest {

    @Autowired
    private DepartmentRepositoryService service;

    @Test
    public void countAllDepartments() {
        assertThat(service.count()).isEqualTo(10L);
    }

    @Test
    public void getAll() {
        assertThat(service.findAll()).hasSize(10).extracting("name").containsExactlyInAnyOrder(
            "Business Development",
            "Engineering",
            "Services",
            "Research and Development",
            "Support",
            "Sales",
            "Marketing",
            "Training",
            "Human Resources",
            "Legal"
        );
    }

    @Test
    public void createDepartmentOK() {
        Department dpt = new Department();
        dpt.setName("New Department");
        Department createdDepartment = service.create(dpt);
        assertThat(createdDepartment).isNotNull()
                                     .hasFieldOrPropertyWithValue("name", "New Department")
                                     .extracting(Department::getId).isNotNull();
        assertThat(service.findById(createdDepartment.getId()))
            .isPresent().get().hasFieldOrPropertyWithValue("name", "New Department")
            .extracting(Department::getId).isEqualTo(createdDepartment.getId());
        service.deleteById(createdDepartment.getId());
    }

    @Test
    public void updateDepartmentOK() {
        Department dpt = service.findById(5L).orElseThrow();
        String initialValue = dpt.getName();
        dpt.setName("Updated Department");
        Department updatedDepartment = service.update(dpt);
        assertThat(updatedDepartment).isNotNull()
                                     .hasFieldOrPropertyWithValue("name", "Updated Department")
                                     .extracting(Department::getId).isNotNull();
        assertThat(service.findById(dpt.getId()))
            .isPresent().get().hasFieldOrPropertyWithValue("name", "Updated Department")
            .extracting(Department::getId).isEqualTo(dpt.getId());
        dpt.setName(initialValue);
        service.update(dpt);
    }

    @Test
    public void deleteEmptyDepartmentOK() {
        Department dpt = new Department();
        dpt.setName("Department To Delete");
        Department dptToDelete = service.create(dpt);
        long initialCount = service.count();
        service.deleteById(dptToDelete.getId());
        assertThat(service.count()).isEqualTo(initialCount - 1);
        assertThat(service.findById(dptToDelete.getId())).isEmpty();
    }


    @Test
    public void deleteDepartmentWithEmployeesOK() {
        Department dptToDelete = service.findAll().get(0);
        long initialCount = service.count();
        service.deleteById(dptToDelete.getId());
        assertThat(service.count()).isEqualTo(initialCount - 1);
        assertThat(service.findById(dptToDelete.getId())).isEmpty();
    }

    @Test
    public void getDepartmentsReportOK(){
        List<DepartmentReport> result = service.getDepartmentsReportAtCurrentDate();
        assertThat(result).hasSize(10);
        assertThat(result.stream().filter(departmentReport -> "Business Development".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(2L);
        assertThat(result.stream().filter(departmentReport -> "Engineering".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(8L);
        assertThat(result.stream().filter(departmentReport -> "Services".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(4L);
        assertThat(result.stream().filter(departmentReport -> "Research and Development".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(2L);
        assertThat(result.stream().filter(departmentReport -> "Support".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(4L);
        assertThat(result.stream().filter(departmentReport -> "Sales".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(6L);
        assertThat(result.stream().filter(departmentReport -> "Marketing".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(8L);
        assertThat(result.stream().filter(departmentReport -> "Training".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(5L);
        assertThat(result.stream().filter(departmentReport -> "Human Resources".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(4L);
        assertThat(result.stream().filter(departmentReport -> "Legal".equals(departmentReport.getName())).map(DepartmentReport::getNumberOfEmployees).findFirst()).isNotEmpty().get().isEqualTo(5L);
    }
}
