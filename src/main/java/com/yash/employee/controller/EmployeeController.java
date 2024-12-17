package com.yash.employee.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.employee.domain.Employee;
import com.yash.employee.exception.DepartmentNotFound;
import com.yash.employee.exception.EmployeeIdNotFoundException;
import com.yash.employee.exception.EmployeeNotFoundException;
import com.yash.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * This method is developed for saving one employee data.
	 * 
	 * @author manik.kore
	 * @param emp
	 * @return
	 */
	@PostMapping("/save")
	public Employee saveEmployee(@RequestBody @Valid Employee emp) {
		return employeeService.saveEmployee(emp);
	}

	/**
	 * This method is developed for saving list of employees.
	 * 
	 * @author manik.kore
	 * @param emp
	 * @return
	 */
	@PostMapping("/saveAll")
	public List<Employee> saveAllEmp(@RequestBody List<Employee> emp) {
		return employeeService.saveAllEmployee(emp);

	}

	/**
	 * This method is developed to retrieve all employees data.
	 * 
	 * @author manik.kore
	 * @return
	 * @throws EmployeeIdNotFoundException 
	 */
	@GetMapping("/getAll")
	public List<Employee> getAllEmployee() throws EmployeeIdNotFoundException {
		return employeeService.getAllEmployee();

	}

	/**
	 * This method is developed to retrieve employee data by employee id.
	 * 
	 * @author manik.kore
	 * @param id
	 * @return
	 * @throws EmployeeIdNotFoundException
	 */
	@GetMapping("/findById/{id}")
	public Optional<Employee> findEmpById(@PathVariable int id) throws EmployeeIdNotFoundException {
		return employeeService.findEmployeeById(id);

	}

	/**
	 * This method is developed to find employee by employee name.
	 * 
	 * @author manik.kore
	 * @param empName
	 * @return
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/findEmpByName/{empName}")
	public Employee getEmployeeByName(@PathVariable String empName) {
		return employeeService.getEmployeeByName(empName);
	}

	/**
	 * This method is developed to find employee by employee department.
	 * 
	 * @author manik.kore
	 * @param empDept
	 * @return
	 * @throws DepartmentNotFound
	 */
	@GetMapping("/findByEmpDept/{empDept}")
	public List<Employee> getEmployeeByDept(@PathVariable String empDept) throws DepartmentNotFound {
		return employeeService.getEmployeeByDept(empDept);
	}

	/**
	 * This method is developed to update employee data.
	 * 
	 * @author manik.kore
	 * @param emp
	 * @return
	 */
	@PutMapping("/update")
	public Employee updateEmp(@RequestBody Employee emp) {
		return employeeService.updateEmployee(emp);
	}

	/**
	 * This method is developed to delete all employees data.
	 * 
	 * @author manik.kore
	 * @return
	 */
	@DeleteMapping("/deleteAll")
	public String deletAllEmp() {
		return employeeService.deleteAllEmployee();
	}

	/**
	 * This method is developed to delete employee by employee id.
	 * 
	 * @author manik.kore
	 * @param id
	 * @return
	 * @throws EmployeeIdNotFoundException
	 */
	@DeleteMapping("/deleteById/{id}")
	public String deletEmpById(@PathVariable int id) throws EmployeeIdNotFoundException {
		return employeeService.deleteEmployeeById(id);
	}

	/**
	 * This method is developed to count employee by their department.
	 * 
	 * @author manik.kore
	 * @return
	 */
	@GetMapping("/empDeptCount")
	public Map<String, Long> empDeptCount() {
		return employeeService.empDeptCount();
	}

	/**
	 * This method is developed to find count of total employee.
	 * 
	 * @author manik.kore
	 * @return
	 */
	@GetMapping("/totalEmployee")
	public Long totalEmployeeCount() {
		return employeeService.totalEmployeeCount();
	}

	/**
	 * This method is developed to find employee having highest salary.
	 * 
	 * @author manik.kore
	 * @return
	 */
	@GetMapping("/highSalariedEmployee")
	public Employee highSalariedEmployee() {
		return employeeService.highSalariedEmployee();

	}

	/**
	 * This method is developed to find nTh highest salary of employee.
	 * 
	 * @author manik.kore
	 * @return
	 */
	@GetMapping("/nThHigestSalary")
	public Employee nThHigestSalary() {
		return employeeService.nThHigestSalary();
	}

	/**
	 * This method is developed to find employee name starts with particular letter.
	 * 
	 * @author manik.kore
	 * @return
	 */
	@GetMapping("/employeeStartsWithName")
	public ResponseEntity<List<Employee>> employeeStartsWithName() throws EmployeeNotFoundException {
		return ResponseEntity.ok(employeeService.employeeStartsWithName());
	}

	/**
	 * This method is developed to find sort employee by ascending order.
	 * 
	 * @author manik.kore
	 * @return
	 */
	@GetMapping("/sortEmployee")
	public String sortEmployee() {
		return employeeService.sortEmployee();
	}

}
