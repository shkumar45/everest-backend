package com.everest.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everest.employee.VO.ResponseTemplateVO;
import com.everest.employee.model.Employee;
import com.everest.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// get all employees
	@GetMapping("/")
	public List<Employee> getAllEmployees() throws InterruptedException {
		// Thread.sleep(500);
		return employeeService.findAll();
	}

	// get employee by id rest api
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);

	}

	@GetMapping("/GetByFirstName/{firstName}")
	public List<Employee> getEmployeesByfirstName(@PathVariable String firstName) {
		return employeeService.getEmployeesByFirstName(firstName);

	}

	// create employee rest api
	@PostMapping("/")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	// update employee rest api
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
			@Valid @RequestBody Employee employeeDetails) {
		Employee employee = employeeService.updateEmployee(id, employeeDetails);

		return ResponseEntity.ok(employee);
	}

	// delete employee rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {

		employeeService.deleteEmployee(id);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/WithDepartment/{id}")
	public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
		log.info("Inside getUserWithDepartment of UserController");
		return employeeService.getUserWithDepartment(userId);
	}
}
