package com.webcustomer.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webcustomer.entity.Customer;
import com.webcustomer.exception.CustomerNotFoundException;
import com.webcustomer.service.CustomerService;
import com.webcustomer.utils.SortField;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(@RequestParam(required=false) String sort) {
		List<Customer> customers = null;
		
		if (Objects.nonNull(sort)) {
			customers = customerService.getCustomers(Integer.parseInt(sort));
		} else {
			customers = customerService.getCustomers(SortField.LAST_NAME.sortIndex);
		}
		return customers;
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer customer = customerService.getCustomerByID(customerId);
		
		if (Objects.isNull(customer)) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		return customer;
	}
}
