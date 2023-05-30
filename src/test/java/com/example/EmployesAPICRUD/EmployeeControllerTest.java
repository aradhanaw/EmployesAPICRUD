package com.example.EmployesAPICRUD;

import com.example.EmployesAPICRUD.model.Employee;
import com.example.EmployesAPICRUD.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc

public class EmployeeControllerTest extends MvcBaseTest {
    ObjectMapper om = new ObjectMapper();

    @Test
    public void createEmployeeWithValidData() throws Exception {
        Employee employee= new Employee();
        employee.setEmpid(17);
        employee.setEmp_name("Aradhana Wagle");
        employee.setEmp_city("kathmandu");
        String jsonRequest =om.writeValueAsString(employee);
        MvcResult result = mvc.perform(post("/api/employees")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")))
                .andExpect(jsonPath("$.data.empid",notNullValue()))
                .andReturn();

    }

    @Test
    public void getEmployeeWithValidId() throws Exception {
        MvcResult result = mvc.perform(get("/api/employees/10")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")))
                .andExpect(jsonPath("$.data.empid",notNullValue()))
                .andReturn();

    }

    @Test
    public void getEmployeeWithInvalidId() throws Exception {
        MvcResult result = mvc.perform(get("/api/employees/20")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("NOT_FOUND")))
                .andExpect(jsonPath("$.data",isEmptyString()))
                .andReturn();

    }
}
