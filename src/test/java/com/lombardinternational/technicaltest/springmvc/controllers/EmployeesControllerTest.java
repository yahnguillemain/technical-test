package com.lombardinternational.technicaltest.springmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lombardinternational.technicaltest.springmvc.api.EmployeeCreationForm;
import com.lombardinternational.technicaltest.springmvc.constant.DepartmentConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeesController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "com.lombardinternational.technicaltest.springmvc.validation",
    "com.lombardinternational.technicaltest.springmvc.errorhandling" })
public class EmployeesControllerTest {

    @Autowired
    public ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreationOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .content(
                                                  mapper.writeValueAsBytes(
                                                      EmployeeCreationForm.builder()
                                                                          .identifier("123-AA")
                                                                          .lastname("Lastname")
                                                                          .age(18)
                                                                          .department(DepartmentConstant.DEPARTMENTS.stream().findFirst().get())
                                                                          .surnames(Arrays.asList("Paul", "John", "Henri"))
                                                                          .build()))
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated()).andExpect(header().string("location", "http://localhost/employees/123-AA"));
    }

    @Test
    public void testCreationFailedMissingLastname() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .content(
                                                  mapper.writeValueAsBytes(
                                                      EmployeeCreationForm.builder()
                                                                          .identifier("123-AA")
                                                                          .age(18)
                                                                          .department(DepartmentConstant.DEPARTMENTS.stream().findFirst().get())
                                                                          .surnames(Arrays.asList("Paul", "John", "Henri"))
                                                                          .build()))
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", equalTo("BAD_REQUEST")))
               .andExpect(jsonPath("$.message", equalTo("Lastname is mandatory")));
    }

    @Test
    public void testCreationFailedMissingSurnames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .content(
                                                  mapper.writeValueAsBytes(
                                                      EmployeeCreationForm.builder()
                                                                          .identifier("123-AA")
                                                                          .age(18)
                                                                          .lastname("Lastname")
                                                                          .department(DepartmentConstant.DEPARTMENTS.stream().findFirst().get())
                                                                          .surnames(Collections.emptyList())
                                                                          .build()))
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", equalTo("BAD_REQUEST")))
               .andExpect(jsonPath("$.message", equalTo("At least one surname must be provided")));
    }

    @Test
    public void testCreationFailedMissingAge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .content(
                                                  mapper.writeValueAsBytes(
                                                      EmployeeCreationForm.builder()
                                                                          .identifier("123-AA")
                                                                          .department(DepartmentConstant.DEPARTMENTS.stream().findFirst().get())
                                                                          .surnames(Arrays.asList("Paul", "John", "Henri"))
                                                                          .lastname("Lastname")
                                                                          .build()))
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", equalTo("BAD_REQUEST")))
               .andExpect(jsonPath("$.message", equalTo("Age is mandatory")));
    }

    @Test
    public void testCreationFailedAgeUnder15() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .content(
                                                  mapper.writeValueAsBytes(
                                                      EmployeeCreationForm.builder()
                                                                          .identifier("123-AA")
                                                                          .department(DepartmentConstant.DEPARTMENTS.stream().findFirst().get())
                                                                          .surnames(Arrays.asList("Paul", "John", "Henri"))
                                                                          .lastname("Lastname")
                                                                          .age(15)
                                                                          .build()))
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", equalTo("BAD_REQUEST")))
               .andExpect(jsonPath("$.message", equalTo("Employee must be at least 16 years old")));
    }

    @Test
    public void testCreationFailedAgeAbove70() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .content(
                                                  mapper.writeValueAsBytes(
                                                      EmployeeCreationForm.builder()
                                                                          .identifier("123-AA")
                                                                          .department(DepartmentConstant.DEPARTMENTS.stream().findFirst().get())
                                                                          .surnames(Arrays.asList("Paul", "John", "Henri"))
                                                                          .lastname("Lastname")
                                                                          .age(71)
                                                                          .build()))
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", equalTo("BAD_REQUEST")))
               .andExpect(jsonPath("$.message", equalTo("Employee must be at max 70 years old")));
    }

    @Test
    public void testCreationFailedAgeDepartmentIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .content(
                                                  mapper.writeValueAsBytes(
                                                      EmployeeCreationForm.builder()
                                                                          .identifier("123-AA")
                                                                          .department("NOTAVALIDDEPT")
                                                                          .surnames(Arrays.asList("Paul", "John", "Henri"))
                                                                          .lastname("Lastname")
                                                                          .age(25)
                                                                          .build()))
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.code", equalTo("BAD_REQUEST")))
               .andExpect(jsonPath("$.message", equalTo("Provided Department does not exists!")));
    }
}
