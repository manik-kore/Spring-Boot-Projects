package com.yash.student.repository.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.yash.student.model.Student;
import com.yash.student.repository.StudentRepository;
import com.yash.student.service.StudentService;

@SpringBootTest
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentService studentService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void test_saveMethod_GivenStudentRecord_ShouldSaveStudent() {
		Student s = new Student(1, "Manik", "manik@gmail.com", "Sangli");
		when(studentRepository.save(any(Student.class))).thenReturn(s);

		Student result = studentService.save(s);
		assertNotNull(result);
		assertEquals(s.getStudentId(), result.getStudentId());

	}

	@Test
	public void testGetStudent() {
		Student s = new Student(1, "Manik", "manik@gmail.com", "Sangli");

		when(studentRepository.save(any(Student.class))).thenReturn(s);
		when(studentRepository.findById(s.getStudentId())).thenReturn(Optional.of(s));

		
		Student s1 = studentService.save(s);
		Student findStudent = studentService.findById((s.getStudentId())).orElse(null);
		assertNotNull(findStudent);
		assertEquals(s1.getStudentId(), findStudent.getStudentId());
		

	}
//
//	@Test
//	public void testGetListOfStudents() {
//		Student s1 = studentRepository.save(new Student(1, "Manik", "manik@gmail.com", "Sangli"));
//		Student s2 = studentRepository.save(new Student(1, "Ajay", "ajay@gmail.com", "Pune"));
//
//		List<Student> result = studentRepository.findAll();
//
//		assertNotNull(result);
//		assertEquals(2, result.size());
//	}
//
//	@Test
//	public void updateStudent() {
//		Student s = studentRepository.save(new Student(1, "Manik", "manik@gmail.com", "Sangli"));
//
//		s.setStudentCiy("Pune");
//		studentRepository.save(s);
//		Student result = studentRepository.findById(s.getStudentId()).orElse(null);
//		assertNotNull(result);
//		assertEquals("Pune", result.getStudentCiy());
//	}
//
//	public void deleteStudent() {
//		Student s = studentRepository.save(new Student(1, "Manik", "manik@gmail.com", "Sangli"));
//
//		studentRepository.deleteById(s.getStudentId());
//		Student result = studentRepository.findById(s.getStudentId()).orElse(null);
//		assertNotNull(result);
//
//	}

}
