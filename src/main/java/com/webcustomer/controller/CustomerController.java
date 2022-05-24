package com.webcustomer.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcustomer.entity.Customer;
import com.webcustomer.service.CustomerService;
import com.webcustomer.utils.SortField;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model, @RequestParam(required=false) String sort) {
		List<Customer> customers = null;
		
		if (Objects.nonNull(sort)) {
			customers = customerService.getCustomers(Integer.parseInt(sort));
		} else {
			customers = customerService.getCustomers(SortField.LAST_NAME.sortIndex);
		}
		
		model.addAttribute("customers", customers);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerID,
									Model model) {
		
		Customer customer = customerService.getCustomerByID(customerID);
		model.addAttribute(customer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerID) {
		customerService.deleteCustomer(customerID);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("customerName") String customerName,
								 Model model) {
		
		List<Customer> customers = customerService.searchCustomers(customerName);
		
		model.addAttribute("customers", customers);
		return "list-customers";
	}
}
