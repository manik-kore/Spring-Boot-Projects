package com.yash.employee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int empId;

	@Pattern(regexp = "^[A-Za-z]{2,10}$", message = "Name should be between 2 to 10 characers and contains only letters")
	private String empName;

	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@yash\\.com$", message = "Invalid email, email should be ends with @yash.com")
	private String empEmail;

	@Pattern(regexp = "^[A-Za-z]{2,20}$", message = "City name should be between 2 to 20 characers and contains only letters")
	private String empCity;

	@Pattern(regexp = "^[A-Za-z]{2,10}$", message = "Department should be between 2 to 10 characers and contains only letters")
	private String empDept;

	@Min(value = 0, message = "Salary must be at least 0")
	@Max(value = 150000, message = "Salary must be less than or equal to 150000")
	private double empSalary;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int empId, String empName, String empEmail, String empCity, String empDept, double empSalary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empCity = empCity;
		this.empDept = empDept;
		this.empSalary = empSalary;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpCity() {
		return empCity;
	}

	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empEmail=" + empEmail + ", empCity=" + empCity
				+ ", empDept=" + empDept + ", empSalary=" + empSalary + "]";
	}

}
