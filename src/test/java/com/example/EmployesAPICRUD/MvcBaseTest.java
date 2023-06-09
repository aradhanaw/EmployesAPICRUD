package com.example.EmployesAPICRUD;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EmployesApicrudApplication.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@ActiveProfiles({"test"})
public class MvcBaseTest {

   protected ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected MockMvc mvc;


    @BeforeEach
    public void setup(){
    }

}