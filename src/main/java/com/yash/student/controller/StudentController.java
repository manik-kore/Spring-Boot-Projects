package com.yash.student.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.student.model.Student;
import com.yash.student.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	  private StudentService studentService;
	
	  @PostMapping("/saveStudent")
	  public Student save(@RequestBody Student student)
	  {
		  
		  
		  
		return studentService.save(student);
		  
	  }
	  
	  @GetMapping("/saveStudent")
	  public Optional<Student> findById(@RequestBody int id)
	  {
		  
		  
		  
		return studentService.findById(id);
		  
	  }

}
