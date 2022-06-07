package com.webcustomer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcustomer.entity.Student;

@Controller
@RequestMapping("/api")
public class StudentController {
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		List<Student> students = new ArrayList<>();
		students.add(new Student("A", "H"));
		
		
		return null;
	}
}
