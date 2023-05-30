package com.example.EmployesAPICRUD.controller;


import com.example.EmployesAPICRUD.model.Employee;
import com.example.EmployesAPICRUD.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public ResponseEntity<Map<String,Object>> createNewEmployee(@RequestBody Employee employee){
        try {
            Employee newEmployee = employeeRepository.save(employee);

            return ResponseEntity.ok(Map.of("status",HttpStatus.OK,"data",newEmployee));
        }catch (Exception e) {
            return ResponseEntity.ok(Map.of("status",HttpStatus.INTERNAL_SERVER_ERROR,"data",""));
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>>getAllEmployees(){
        List<Employee> empList = new ArrayList<>();
        employeeRepository.findAll().forEach(empList::add);
        return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
    }

    @GetMapping("/employees/{empid}")
    public ResponseEntity<Map<String, Object>>getEmployeeById(@PathVariable long empid){
       Optional<Employee> emp = employeeRepository.findById(empid);
       if(emp.isPresent()){
           return ResponseEntity.ok(Map.of("status",HttpStatus.OK,"data",emp));
       }else{
           return ResponseEntity.ok(Map.of("status",HttpStatus.NOT_FOUND,"data",""));
       }

    }

    @PutMapping("/employees/{empid}")
    public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee){
       Optional<Employee> emp = employeeRepository.findById(empid);
       if(emp.isPresent()){
           Employee existEmp = emp.get();
           existEmp.setEmp_age(employee.getEmp_age());
           existEmp.setEmp_city(employee.getEmp_city());
           existEmp.setEmp_name(employee.getEmp_name());
           existEmp.setEmp_salary(employee.getEmp_salary());
           employeeRepository.save(existEmp);
           return "Employee Details against Id "+ empid+ "updated";

       }
       else{
           return "Employee Details does not exist for empid"+ empid;
       }
    }

    @DeleteMapping("/employees/{empid}")
    public String deleteEmployeeByEmpId(@PathVariable Long empid){
        employeeRepository.deleteById(empid);
        return "Employee Deleted Successfully";
    }

    @DeleteMapping("/employee")
    public String deleteAllEmployee(){
        employeeRepository.deleteAll();
        return "Employee deleted Successfully";
    }



}
