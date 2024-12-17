package com.yash.student.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.student.model.Student;
import com.yash.student.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	public Student save(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	public Optional<Student> findById(int id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id);
	}
	
	

}
