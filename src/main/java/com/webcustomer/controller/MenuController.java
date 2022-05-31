package com.webcustomer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@GetMapping("/manager")
	public String showManagerPanel() {
		return "manager";
	}
	
	@GetMapping("/admin")
	public String showAdminPanel() {
		return "admin";
	}
}
