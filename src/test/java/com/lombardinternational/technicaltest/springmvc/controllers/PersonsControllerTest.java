package com.lombardinternational.technicaltest.springmvc.controllers;

import com.lombardinternational.technicaltest.springmvc.domain.Person;
import com.lombardinternational.technicaltest.springmvc.mapper.PersonPatcher;
import com.lombardinternational.technicaltest.springmvc.service.PersonsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Import(WebSecurityConfiguration.class)
@WebMvcTest(controllers = PersonsController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class })
@ComponentScan(basePackageClasses = { PersonsService.class, PersonPatcher.class })
public class PersonsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonsService service;

    @BeforeEach
    public void initPersons() {
        service.setPersons(IntStream.range(0, 20).mapToObj(i -> Person.builder()
                                                                      .firstname(String.format("Firstname_%s", i))
                                                                      .lastname(String.format("Lastname_%s", i))
                                                                      .modifiable(Boolean.TRUE)
                                                                      .build()).collect(Collectors.toList()));
    }

    /**
     * Expected Result : the full list of persons must be returned on GET /persons
     *
     * @throws Exception
     */
    @Test
    void testGetPersons() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                                                                     .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                                      .andExpect(jsonPath("$").exists())
                                      .andExpect(jsonPath("$").isArray())
                                      .andExpect(jsonPath("$", hasSize(service.getAll().size())));
        AtomicInteger id = new AtomicInteger(0);
        for (Person p : service.getAll()) {
            result.andExpect(jsonPath(String.format("$[%d].firstname", id.get()), Matchers.equalTo(p.getFirstname())))
                  .andExpect(jsonPath(String.format("$[%d].lastname", id.get()), Matchers.equalTo(p.getLastname())))
                  .andExpect(jsonPath(String.format("$[%d].modifiable", id.getAndIncrement())).doesNotExist())
            ;
        }
    }

    /**
     * Expected Result : the person with id {id} is returned on call to endpoint GET /persons/{id}
     *
     * @throws Exception
     */
    @ParameterizedTest
    @ValueSource(ints = { 2, 7, 15 })
    void testGetOnePerson(int id) throws Exception {
        Person expectedResult = service.get(id).orElseThrow();
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/{id}", id)
                                              .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
               .andExpect(jsonPath("$.firstname", equalTo(expectedResult.getFirstname())))
               .andExpect(jsonPath("$.lastname", equalTo(expectedResult.getLastname())))
               .andExpect(jsonPath("$.modifiable").doesNotExist());
    }

    /**
     * Expected Result : On GET person on an unexisting id, a 404 NOT FOUND error must be returned by the server
     *
     * @throws Exception
     */
    @Test
    void testGetOnePersonNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/{id}", 145)
                                              .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    /**
     * Expected Result : Must return the total number of persons
     *
     * @throws Exception
     */
    @Test
    void testCountPerson() throws Exception {
        long currentSize = service.count();
        mockMvc.perform(MockMvcRequestBuilders.head("/persons")
                                              .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent())
               .andExpect(header().longValue("total", currentSize))
        ;
    }

    /**
     * Expected Result : A person is created
     *
     * @throws Exception
     */
    @Test
    void testCreatePerson() throws Exception {
        long initialSize = service.count();
        mockMvc.perform(MockMvcRequestBuilders.post("/persons")
                                              .content("{\"firstname\":\"CreatedOneFirstname\",\"lastname\":\"CreatedOneLastname\"}")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andExpect(header().string("Location", "http://localhost/persons/" + initialSize));
        assertThat(service.count()).isEqualTo(initialSize + 1);
        assertThat(service.get(Long.valueOf(initialSize).intValue())).isPresent().get()
                                                                     .hasFieldOrPropertyWithValue("firstname", "CreatedOneFirstname")
                                                                     .hasFieldOrPropertyWithValue("lastname", "CreatedOneLastname");
    }

    /**
     * Update of a person
     * @throws Exception
     */
    @Test
    void testUpdatePerson() throws Exception {
        long initialSize = service.count();
        mockMvc.perform(MockMvcRequestBuilders.put("/persons/{id}", 12)
                                              .content(
                                                  "{\"firstname\":\"UpdatedOneFirstname\",\"lastname\":\"UpdatedOneLastname\"}")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
        assertThat(service.count()).isEqualTo(initialSize);
        assertThat(service.get(12)).isPresent().get()
                                   .hasFieldOrPropertyWithValue("firstname", "UpdatedOneFirstname")
                                   .hasFieldOrPropertyWithValue("lastname", "UpdatedOneLastname");
    }

    /**
     * Expected Result: on attempt to modify a user in read only, a 403 error must be returned by the server
     * @throws Exception
     */
    @Test
    void testUpdatePersonNotModifiableError() throws Exception {
        int id = 4;
        service.patchPerson(id, Person.builder().modifiable(false).build());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/persons/{id}", id)
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(
                                                                         "{\"firstname\":\"UpdatedOneFirstname\",\"lastname\":\"UpdatedOneLastname\"}")
                                                                     .accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden())
                                      .andExpect(jsonPath("$.message", equalTo(String.format("Person with id %s is not modifiable", id))));

    }

    /**
     *
     * @throws Exception
     */
    @Test
    void testPatchPerson() throws Exception {
        long initialSize = service.count();
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.patch("/persons/{id}", 7)
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(
                                                                         "{\"firstname\":\"PatchedOneFirstname\"}")
                                      )
                                      .andExpect(status().isOk());
        assertThat(service.count()).isEqualTo(initialSize);
        assertThat(service.get(7)).isPresent().get()
                                  .hasFieldOrPropertyWithValue("firstname", "PatchedOneFirstname")
                                  .hasFieldOrPropertyWithValue("lastname", "Lastname_7");
    }

    @Test
    void testDeletePerson() throws Exception {
        long initialSize = service.count();
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/{id}", 14)).andExpect(status().isOk());
        assertThat(service.count()).isEqualTo(initialSize - 1);
        assertThat(service.getAll()).extracting(Person::getFirstname).doesNotContain("Firstname_14");
    }
}
