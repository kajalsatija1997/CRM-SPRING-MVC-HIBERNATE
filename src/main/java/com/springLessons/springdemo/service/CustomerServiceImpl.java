package com.springLessons.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springLessons.springdemo.dao.CustomerDAO;
import com.springLessons.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	//need to inject customer dao
	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		
		return customerDao.getCustomers(theSortField);
		
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		customerDao.saveCustomer(theCustomer);
		
		System.out.println("Customer successfully saved. "+theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		return customerDao.getCustomer(customerId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDao.deleteCustomer(theId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		 return customerDao.searchCustomers(theSearchName);
	}
	
	

}
