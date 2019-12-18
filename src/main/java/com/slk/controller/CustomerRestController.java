package com.slk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slk.DAO.CustomerDAO;
import com.slk.model.Customer;



@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class CustomerRestController {
	@Autowired
	private CustomerDAO customerDAO;
	private Customer customer;
	@GetMapping("/customer1")
	public List getAllCustomers() throws Exception {
		System.out.println("function inside customer rest");
		return customerDAO.getAllCustomers();
	}
	@PutMapping("/put/customer/{cust_id}")
	public ResponseEntity updateCustomer(@PathVariable int cust_id, @RequestBody Customer customer) {
		
		// if (null == customer) {
		// return new ResponseEntity("No Customer found for ID " + id,
		// HttpStatus.NOT_FOUND);
		// }
		customerDAO.updateCustomer(customer);
		return new ResponseEntity(customer, HttpStatus.OK);
	}

}
