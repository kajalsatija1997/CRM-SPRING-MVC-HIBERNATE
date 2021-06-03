package com.springLessons.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springLessons.springdemo.entity.Customer;
import com.springLessons.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	
	//Txns will handled by service layer
	/* @Transactional */
	public List<Customer> getCustomers(int theSortField) {
		
		//Create a session
		Session currentSession=sessionFactory.getCurrentSession();
		
		String theFieldName=null;
		
		switch(theSortField)
		{
		case SortUtils.FIRST_NAME:
			theFieldName= "firstName";
			break;
			
		case SortUtils.LAST_NAME:
			theFieldName= "lastName";
			break;
			
		case SortUtils.EMAIL:
			theFieldName= "email";
			break;
			
		default:
			theFieldName= "lastName";
		}
		
		//Create a query to fetch customer list
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by "+ theFieldName,Customer.class);
		
		//get the result list
		List<Customer> customers=theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
	
		//Create a session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//Save the customer --if only save op
		//Now we have to ops to perform on same button - Add/Update	
		/* currentSession.save(theCustomer); */
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		
				//Create a session
				Session currentSession=sessionFactory.getCurrentSession();
				
				//get the customer based on its id
				Customer customer=currentSession.get(Customer.class,customerId);
				
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session curentSession=sessionFactory.getCurrentSession();
		
		Query theQuery= curentSession.createQuery("delete from Customer where id=:theCustomerId");
		
		theQuery.setParameter("theCustomerId", theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

	
}
