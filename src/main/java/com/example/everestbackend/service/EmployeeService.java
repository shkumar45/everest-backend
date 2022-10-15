package com.example.everestbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.everestbackend.exception.ResourceNotFoundException;
import com.example.everestbackend.model.Employee;
import com.example.everestbackend.repository.EmployeeRepository;

@Component
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {

		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
	}

	public Employee createEmployee(Employee employee) {

		return employeeRepository.save(employee);

	}

	public Employee updateEmployee(Long id, Employee employeeDetails) {

		Employee employee = this.getEmployeeById(id);

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());

		Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}

	public void deleteEmployee(Long id) {
		Employee employee = this.getEmployeeById(id);

		employeeRepository.delete(employee);

	}
}
