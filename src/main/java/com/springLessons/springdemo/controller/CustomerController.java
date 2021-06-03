package com.springLessons.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.springLessons.springdemo.dao.CustomerDAO;
import com.springLessons.springdemo.entity.Customer;
import com.springLessons.springdemo.service.CustomerService;
import com.springLessons.springdemo.util.SortUtils;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//inject the Service  into controller
	@Autowired
	private CustomerService customerService;
	
	//Changing it to @GetMapping
	/* @RequestMapping("/list") */
	@GetMapping("/list")
	public String listCustomers(Model theModel,@RequestParam(required=false) String sort)
	{
		//get customers from the dao
		List<Customer> customerList=null;
		
		//check for sort field
		if(sort!=null) {
			int theSortField=Integer.parseInt(sort);
			customerList= customerService.getCustomers(theSortField);
		}
		else
		{
			customerList= customerService.getCustomers(SortUtils.LAST_NAME);
		}
			
		//add the customers to the model
		theModel.addAttribute("customers",customerList);
		
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		//Create model attribute to bind form data
		Customer theCustomer=new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		//save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId,Model theModel)
	{
		
		//get the customer form the Db
		Customer tempCustomer=customerService.getCustomer(customerId);
		
		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer",tempCustomer);
		
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int customerId)
	{
		customerService.deleteCustomer(customerId);
		
		//send over to our form
		return "redirect:/customer/list";
	}
	
	 @GetMapping("/search")
	    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
	                                    Model theModel) {
	        // search customers from the service
	        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
	                
	        // add the customers to the model
	        theModel.addAttribute("customers", theCustomers);
	        return "list-customers";        
	    }
}
