package com.yash.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
