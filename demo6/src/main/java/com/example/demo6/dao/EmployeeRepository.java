package com.example.demo6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example2.demo2.dto.Employee;

@Repository
public interface EmployeeRepository  extends  JpaRepository<Employee,Long>{

}
