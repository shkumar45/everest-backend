package com.everest.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everest.employee.VO.Department;
import com.everest.employee.VO.ResponseTemplateVO;
import com.everest.employee.exception.ResourceNotFoundException;
import com.everest.employee.model.Employee;
import com.everest.employee.repository.EmployeeRepository;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RestTemplate restTemplate;

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
		employee.setDepartmentId(employeeDetails.getDepartmentId());

		Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}

	public void deleteEmployee(Long id) {
		Employee employee = this.getEmployeeById(id);

		employeeRepository.delete(employee);

	}

	public ResponseTemplateVO getUserWithDepartment(Long employeeId) {
		log.info("Inside getUserWithDepartment of UserService");
		ResponseTemplateVO vo = new ResponseTemplateVO();
		Employee employee = this.getEmployeeById(employeeId);

		Department department = restTemplate
				.getForObject("http://DEPARTMENT-SERVICE/api/v1/departments/" + employee.getDepartmentId(),
						Department.class);

		vo.setEmployee(employee);
		vo.setDepartment(department);

		return vo;
	}
}
