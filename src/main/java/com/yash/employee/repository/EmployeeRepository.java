package com.yash.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.employee.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	/**
	 * This is business logic or customized method which is not present inside
	 * JPA Repository.
	 * Here method name should be start with findBy and suffix with entity class
	 * property name like findByEmpName. property is empName but here just make EmpName. 
	 * @author manik.kore
	 * @param empName
	 * @return
	 */
	Employee findByEmpName(String empName);

	
	/**
	 * This is business logic or customized method which is not present inside
	 * JPA Repository.
	 * Here method name should be start with findBy and suffix with entity class
	 * property name like findByEmpDept. property is empDept but here just make EmpDept.
	 * @author manik.kore
	 * @param empDept
	 * @return
	 */
	List<Employee> findByEmpDept(String empDept);


}
