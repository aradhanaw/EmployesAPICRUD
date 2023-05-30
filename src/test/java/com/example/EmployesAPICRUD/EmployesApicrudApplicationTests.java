package com.example.EmployesAPICRUD;

import com.example.EmployesAPICRUD.model.Employee;
import com.example.EmployesAPICRUD.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@SpringBootTest
public class EmployesApicrudApplicationTests {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@Before
	public  void setUp(){
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee= new Employee();
		employee.setEmp_name("Aradhana Wagle");
		employee.setEmp_city("kathmandu");
		String jsonRequest =om.writeValueAsString(employee);
		MvcResult result= mockMvc.perform(post("/api/employees").content(jsonRequest).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		Response response =om.readValue(resultContext, Response.class);
		Assert.assertTrue(response.isStatus() ==Boolean.TRUE);


	}



}
