package com.yash.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yash.student.StudentDemoApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StudentDemoApplication.class)
class StudentDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
