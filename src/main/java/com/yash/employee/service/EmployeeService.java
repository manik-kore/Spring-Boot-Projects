package com.yash.employee.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.employee.domain.Employee;
import com.yash.employee.exception.DepartmentNotFound;
import com.yash.employee.exception.EmployeeIdNotFoundException;
import com.yash.employee.exception.EmployeeNotFoundException;
import com.yash.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * This method is developed for saving one employee data.
	 * 
	 * @author manik.kore
	 * @param emp
	 * @return
	 */
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	/**
	 * This method is developed for saving list of employees.
	 * 
	 * @author manik.kore
	 * @param emp
	 * @return
	 */
	public List<Employee> saveAllEmployee(List<Employee> emp) {
		// TODO Auto-generated method stub
		return employeeRepository.saveAll(emp);
	}

	/**
	 * This method is developed to retrieve all employees data.
	 * 
	 * @author manik.kore
	 * @return
	 * @throws EmployeeIdNotFoundException 
	 */
	public List<Employee> getAllEmployee() throws EmployeeIdNotFoundException {
		
		List<Employee> allEmployee = employeeRepository.findAll();
		if(allEmployee.isEmpty())
		{
			throw new EmployeeIdNotFoundException("Employee List Is Empty"); 
		}
		
		return employeeRepository.findAll();

	}

	/**
	 * This method is developed to retrieve employee data by employee id.
	 * 
	 * @author manik.kore
	 * @param id
	 * @return
	 * @throws EmployeeIdNotFoundException
	 */
	public Optional<Employee> findEmployeeById(int id) throws EmployeeIdNotFoundException {
		// TODO Auto-generated method stub
		List<Employee> emp = employeeRepository.findAll();
		boolean idExists = emp.stream().anyMatch(e -> e.getEmpId() == id);

		if (idExists) {
			return employeeRepository.findById(id);
		}
		{
			throw new EmployeeIdNotFoundException("No Employee Is Found With Given Id-: " + id);
		}

	}

	/**
	 * This method is developed to find employee by employee name.
	 * 
	 * @author manik.kore
	 * @param empName
	 * @return
	 * @throws EmployeeNotFoundException
	 */
	public Employee getEmployeeByName(String empName) {
		// TODO Auto-generated method stub
		List<Employee> emp = employeeRepository.findAll();
		boolean nameExists = emp.stream().anyMatch(e -> e.getEmpName().equalsIgnoreCase(empName));
		if (nameExists) {
			return employeeRepository.findByEmpName(empName);
		} else {
			throw new EmployeeNotFoundException("No Such Employee Is Found Having Name-: " + empName);
		}
	}

	/**
	 * This method is developed to find employee by employee department.
	 * 
	 * @author manik.kore
	 * @param empDept
	 * @return
	 * @throws DepartmentNotFound 
	 */
	public List<Employee> getEmployeeByDept(String empDept) throws DepartmentNotFound {
		// TODO Auto-generated method stub
		List<Employee> emp = employeeRepository.findAll();
		boolean deptExists = emp.stream().anyMatch(e -> e.getEmpDept().equalsIgnoreCase(empDept));
		if(deptExists) {
			return employeeRepository.findByEmpDept(empDept);

		} else {
			throw new DepartmentNotFound("No Such Department Is Found Having Name-: " + empDept);
		}
	}

	/**
	 * This method is developed to update employee data.
	 * 
	 * @author manik.kore
	 * @param emp
	 * @return
	 */
	public Employee updateEmployee(Employee emp) {

		Employee existingEmployee = employeeRepository.findById(emp.getEmpId()).orElse(null);

		existingEmployee.setEmpName(emp.getEmpName());
		existingEmployee.setEmpEmail(emp.getEmpEmail());
		existingEmployee.setEmpCity(emp.getEmpCity());
		existingEmployee.setEmpDept(emp.getEmpDept());
		existingEmployee.setEmpSalary(emp.getEmpSalary());
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	/**
	 * This method is developed to delete all employees data.
	 * 
	 * @author manik.kore
	 * @return
	 */
	public String deleteAllEmployee() {
		// TODO Auto-generated method stub
		employeeRepository.deleteAll();
		return "Deleted All Employees Successfully";
	}

	/**
	 * This method is developed to delete employee by employee id.
	 * 
	 * @author manik.kore
	 * @param id
	 * @return
	 * @throws EmployeeIdNotFoundException 
	 */
	public String deleteEmployeeById(int id) throws EmployeeIdNotFoundException {
		// TODO Auto-generated method stub
		List<Employee> emp = employeeRepository.findAll();
		boolean idExists = emp.stream().anyMatch(e -> e.getEmpId() == id);

		if (idExists) {
			 employeeRepository.deleteById(id);
			 return "Delete Employee With Id -: " + id + " Successfully";
		}
		else
		{
			throw new EmployeeIdNotFoundException("No Employee Is Found With Given Id-: " + id);
		}
//		employeeRepository.deleteById(id);
//		return "Delete Student With Id -: " + id + " Successfully";
	}

	/**
	 * This method is developed to count employee by their department. In this
	 * method Java 8 Stream API is used.
	 * 
	 * @author manik.kore
	 * @return
	 */
	public Map<String, Long> empDeptCount() {
		// TODO Auto-generated method stub
		List<Employee> allEmployee = employeeRepository.findAll();
		Map<String, Long> empDeptCount = allEmployee.stream().distinct()
				.collect(Collectors.groupingBy(Employee::getEmpDept, Collectors.counting()));
		System.out.println(empDeptCount);
		return empDeptCount;
	}

	/**
	 * This method is developed to find count of total employee. In this method Java
	 * 8 Stream API is used.
	 * 
	 * @author manik.kore
	 * @return
	 */
	public Long totalEmployeeCount() {
		// TODO Auto-generated method stub

		List<Employee> allEmployee = employeeRepository.findAll();
		Long totalEmployee = allEmployee.stream().count();
		System.out.println("Total Employees Are In Company-: " + totalEmployee);
		return totalEmployee;

	}

	/**
	 * This method is developed to find employee having highest salary. In this
	 * method Java 8 Stream API is used.
	 * 
	 * @author manik.kore
	 * @return
	 */
	public Employee highSalariedEmployee() {
		// TODO Auto-generated method stub
		List<Employee> allEmployee = employeeRepository.findAll();
		Employee highSalariedEmployee = allEmployee.stream()
				.sorted(Comparator.comparing(Employee::getEmpSalary).reversed()).findFirst().get();

		System.out.println("Employee Having Highest Salary Is-: " + highSalariedEmployee);

		return highSalariedEmployee;
	}

	/**
	 * This method is developed to find nTh highest salary of employee. In this
	 * method Java 8 Stream API is used.
	 * 
	 * @author manik.kore
	 * @return
	 */
	public Employee nThHigestSalary() {
		// TODO Auto-generated method stub

		List<Employee> allEmployee = employeeRepository.findAll();
		Employee nThHigestSalary = allEmployee.stream()
				.sorted(Comparator.comparingDouble(Employee::getEmpSalary).reversed()).skip(1).findFirst().get();
		System.out.println("Employe Having Second Higest Salary Is-: " + nThHigestSalary);
		return nThHigestSalary;
	}

	/**
	 * This method is developed to find employee name starts with particular letter.
	 * In this method Java 8 Stream API is used. Proper Exception is handled with
	 * exception propagation.
	 * 
	 * @author manik.kore
	 * @return
	 */
	public List<Employee> employeeStartsWithName() throws EmployeeNotFoundException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Employee Name To Search-: ");
		String searchEmp = sc.next();

		List<Employee> allEmployee = employeeRepository.findAll();
//		List<Employee> employeeStartsWithName = allEmployee.stream().filter(e -> e.getEmpName().startsWith(searchEmp))
//				.collect(Collectors.toList());
		List<Employee> employeeStartsWithName = allEmployee.stream()
				.filter(e -> e.getEmpName().equalsIgnoreCase(searchEmp)).collect(Collectors.toList());

		if (employeeStartsWithName.isEmpty()) {
			throw new EmployeeNotFoundException("No Employee Is Present With Such Name");
		} else {
			return employeeStartsWithName;
		}

	}

	/**
	 * This method is developed to find sort employee by ascending order. In this
	 * method Java 8 Stream API is used.
	 * 
	 * @author manik.kore
	 * @return
	 */
	public String sortEmployee() {
		// TODO Auto-generated method stub
		List<Employee> allEmployee = employeeRepository.findAll();
		String sortEmployee = allEmployee.stream().sorted(Comparator.comparing(Employee::getEmpName))
				.collect(Collectors.toList()).toString();
		return sortEmployee.toString();
	}

}
