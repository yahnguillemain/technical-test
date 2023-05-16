package com.lombardinternational.technicaltest.springmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lombardinternational.technicaltest.springmvc.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CounterController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class })
@Import(AppConfig.class)
public class CounterControllerTest {

    @Autowired
    public ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    /**
     * Here we increment the counter and it should now be 1
     *
     * @throws Exception
     */
    @Test
    public void testGetAndThenIncrementOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/counter"))
               .andExpect(status().isOk()).andExpect(jsonPath("$.result", equalTo(0)));
        mockMvc.perform(MockMvcRequestBuilders.patch("/counter"))
               .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/counter"))
               .andExpect(status().isOk()).andExpect(jsonPath("$.result", equalTo(1)));
    }

}
