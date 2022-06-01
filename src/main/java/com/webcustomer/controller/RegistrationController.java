package com.webcustomer.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcustomer.entity.User;
import com.webcustomer.service.UserService;
import com.webcustomer.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	private Map<String, String> roles;
	
	@PostConstruct
	protected void loadRoles() {
		roles = new LinkedHashMap<String, String>();
		roles.put("ROLE_EMPLOYEE", "Employee");
		roles.put("ROLE_MANAGER", "Manager");
		roles.put("ROLE_ADMIN", "Admin");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationPage(Model model) {
		model.addAttribute("crmUser", new CrmUser());
		model.addAttribute("roles", roles);
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser,
					BindingResult bindingResult,
					Model model) {
		
		if (bindingResult.hasErrors()) {
			return "registration-form";
		}
		
		User user = userService.findByUserName(crmUser.getUserName());
		if (user != null) {
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("roles", roles);
			model.addAttribute("registrationError", "Username already exists.");
			return "registration-form";
		}
		
		userService.saveUser(crmUser);
		return "registration-confirmation";
	}
}
