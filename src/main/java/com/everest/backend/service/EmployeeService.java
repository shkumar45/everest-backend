package com.everest.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everest.backend.exception.ResourceNotFoundException;
import com.everest.backend.model.Employee;
import com.everest.backend.repository.EmployeeRepository;

@Service
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

	public List<Employee> getEmployeesByFirstName(String firstName) {

		return employeeRepository.findByFirstName(firstName);
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
