package com.springLessons.springdemo.service;

import java.util.List;


import com.springLessons.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers(int theSortField);

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String theSearchName);
	
}
