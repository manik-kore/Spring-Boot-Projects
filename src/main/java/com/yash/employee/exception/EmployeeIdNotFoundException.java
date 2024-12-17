package com.yash.employee.exception;

public class EmployeeIdNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmployeeIdNotFoundException(String msg)
	{
		super(msg);
	}
	
}
