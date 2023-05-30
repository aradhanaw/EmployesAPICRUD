package com.example.EmployesAPICRUD.repository;

import com.example.EmployesAPICRUD.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
