package com.yash.student.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.yash.student.model.Student;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	   public void test_saveMethod_GivenStudentRecord_ShouldSaveStudent()
	   {
		   Student s = new Student(1,"Manik","manik@gmail.com","Sangli");
		   
		   Student result = studentRepository.save(s);
		   assertNotNull(result);
		   
	   }
	
	@Test 
	public void testGetStudent()
	{
		Student s = studentRepository.save(new Student(1,"Manik","manik@gmail.com","Sangli"));
		
		Student s1 = studentRepository.findById(s.getStudentId()).orElse(null);
		
		assertNotNull(s1);
		assertEquals(s.getStudentId(), s1.getStudentId());
		
	}
	
	@Test
	public void testGetListOfStudents()
	{
		Student s1 = studentRepository.save(new Student(1,"Manik","manik@gmail.com","Sangli"));
		Student s2 = studentRepository.save(new Student(1,"Ajay","ajay@gmail.com","Pune"));
		
		List<Student> result = studentRepository.findAll();
		
		assertNotNull(result);
		assertEquals(2, result.size());
	}
	
	@Test
	public void updateStudent()
	{
		Student s = studentRepository.save(new Student(1,"Manik","manik@gmail.com","Sangli"));
		
		s.setStudentCiy("Pune");
		studentRepository.save(s);
		Student result = studentRepository.findById(s.getStudentId()).orElse(null);
		assertNotNull(result);
		assertEquals("Pune",result.getStudentCiy());
	}
	
	public void deleteStudent()
	{
		Student s = studentRepository.save(new Student(1,"Manik","manik@gmail.com","Sangli"));
		
		studentRepository.deleteById(s.getStudentId());
		Student result = studentRepository.findById(s.getStudentId()).orElse(null);
		assertNotNull(result);
		
	}
	
}
