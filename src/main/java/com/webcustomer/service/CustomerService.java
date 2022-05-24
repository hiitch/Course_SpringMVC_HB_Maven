package com.webcustomer.service;

import java.util.List;


import com.webcustomer.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers(int sortField);
	
	public void saveCustomer(Customer customer);
	
	public Customer getCustomerByID(int id);

	public void deleteCustomer(int customerID);

	public List<Customer> searchCustomers(String customerName);
}
