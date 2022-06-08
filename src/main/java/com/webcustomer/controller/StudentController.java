package com.webcustomer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webcustomer.entity.Student;
import com.webcustomer.exception.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	private List<Student> students;
	
	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();
		students.add(new Student("A", "H"));
		students.add(new Student("S", "H"));
		students.add(new Student("H", "M"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudents(@PathVariable int studentId) {
		
		if (studentId >= students.size() || studentId < 0) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		return students.get(studentId);
	}
}
