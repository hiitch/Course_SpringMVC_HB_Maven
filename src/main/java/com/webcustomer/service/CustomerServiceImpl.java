package com.webcustomer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webcustomer.dao.CustomerDAO;
import com.webcustomer.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int sortField) {
		return customerDAO.getCustomers(sortField);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomerByID(int id) {
		return customerDAO.getCustomerByID(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerID) {
		customerDAO.deleteCustomer(customerID);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String customerName) {
		return customerDAO.searchCustomers(customerName);
	}
}
