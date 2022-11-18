package com.everest.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	@Size(min = 2, max = 25, message = "First Name should be of length between 2 and 25")
	private String firstName;

	@Size(min = 2, max = 25)
	@Column(name = "last_name")
	private String lastName;

	@Email
	@Size(min = 2, max = 25)
	@Column(name = "email")
	private String email;

	@Column(name = "department_id")
	private long departmentId;

}
