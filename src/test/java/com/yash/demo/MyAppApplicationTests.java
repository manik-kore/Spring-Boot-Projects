package com.yash.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yash.employee.MyAppApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyAppApplication.class)
class MyAppApplicationTests {

	@Test
	void contextLoads() {
	}

}
