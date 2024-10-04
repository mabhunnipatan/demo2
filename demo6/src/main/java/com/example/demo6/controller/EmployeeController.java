package com.example.demo6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example2.demo2.controller.ResourceNotFoundException;
import com.example2.demo2.dao.EmployeeRepository;
import com.example2.demo2.dto.Employee;

@RestController
@RequestMapping("/api/v1")

public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	/*
	 * URL for Create or insert employee Object into DB
	 * http://localhost:9090/api/v1/employees
	 * 
	 * @RequestMapping(method = RequestMethod.POST).
	 *
	 */
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	/*
	 * GET All Employees http://localhost:9090/api/v1/employees
	 */ @GetMapping("/employee")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();

	}

		@GetMapping("/employees/{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id)
				throws ResourceNotFoundException {
			
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id : " + id));

			return ResponseEntity.ok().body(employee);
		}
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,
				@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
			
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id : " + id));

			employee.setFname(employeeDetails.getFname());
			employee.setLname(employeeDetails.getLname());
			employee.setEmail(employeeDetails.getEmail());

			final Employee updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);

		}
		@DeleteMapping("employees/{id}")
		public ResponseEntity<String>deleteEmployee(@PathVariable("id") Long id) throws ResourceNotFoundException{

			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id : " + id));
			employeeRepository.deleteById(id);
			
			 return ResponseEntity.ok("deleted successfully"); 
			
		}


}
