package com.slk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slk.DAO.CustomerDAO;
import com.slk.model.Agent;
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
	
	@PostMapping(value="/put/customer")
	public ResponseEntity updateCustomer(@RequestBody Customer customer) {
		customerDAO.updateCustomer(customer);
		return new ResponseEntity(customer, HttpStatus.OK);
	}
}
