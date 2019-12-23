package com.slk.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.slk.DAO.D_CustomerImpl;
import com.slk.DAO.D_CustomerInterface;
import com.slk.model.CustomerAccount;
import com.slk.model.D_Customer;
import com.slk.model.D_Loan;
import com.slk.model.D_Transaction;
import com.slk.model.LoanAccount;
import com.slk.model.TransferClass;
@CrossOrigin(origins="http://localhost:4200")
@RestController
//@RequestMapping("/customerService")

public class D_CustomerRestController {
	
	@Autowired
	
	D_CustomerInterface ci=new D_CustomerImpl();

	@GetMapping("/Customer")
		public List getCustomers() {
		
		return ci.viewAllCustomer();
	}

	@GetMapping("/Customer/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") long id) {
		
		List<D_Customer> customer=ci.viewProfile(id);
			
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
	//********************  Login   ****************************
	@GetMapping("/Customer/{username}/{password}")
	public ResponseEntity getCustomerLogin(@PathVariable("username") String username,@PathVariable("password") String password) {
		
		int flag=ci.loginValidation(username,password);
			
		if (flag == 0) {
			return new ResponseEntity("No Customer found for ID " + username, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(flag, HttpStatus.OK);
	}
	//
	
	@GetMapping("/Customer/Balance/{id}")
	public ResponseEntity getCustomerBalance(@PathVariable("id") long id) {
		
		List<CustomerAccount> listCustAcc=ci.viewBalance(id);
			
		if (listCustAcc == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(listCustAcc, HttpStatus.OK);
	}
	
	
	@GetMapping("/Customer/Loan/{id}")
	public ResponseEntity getCustomerLoan(@PathVariable("id") int id) {
		
		List<LoanAccount> loan=ci.viewLoan(id);
			
		if (loan == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(loan, HttpStatus.OK);
	}
	
	
	@GetMapping("/Customer/Transaction/{id}")
	public ResponseEntity getCustomerTransaction(@PathVariable("id") int id) {
		
		List<D_Transaction> t=ci.transactionhistory(id);;
			
		if (t == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(t, HttpStatus.OK);
	}
	
	
	
	
	@PutMapping(value = "/Customer/CheckTransfer/{id}")
		public ResponseEntity checkTransfer(@PathVariable("id") int id,@RequestBody CustomerAccount custAcc) {
		
		TransferClass tc=ci.checkTransfer(id,custAcc);
			
		if (tc == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(tc, HttpStatus.OK);
	}
	
	@PutMapping("/Customer/Transfer/{amount}")
	public ResponseEntity getCustomerTransfer(@PathVariable("amount") double amount,@RequestBody TransferClass transAcc) {
		
		int flag=ci.transfer(amount,transAcc);
			
		if (flag == 0) {
			return new ResponseEntity("No Customer found for ID " + transAcc.getSenderAccount(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(flag, HttpStatus.OK);
	}
	
	@PutMapping(value = "/put/Customer/{id}")
	public ResponseEntity createCustomer(@PathVariable("id") long id,@RequestBody D_Customer cust ) {
		
		ci.updateprofile(id, cust);
		return new ResponseEntity(cust, HttpStatus.OK);
	}
	
	@PutMapping(value = "/put/Customer/Loan/{id}/{accNo}")
	public ResponseEntity requestLoan1(@PathVariable("id") long cust_id,@PathVariable("accNo") long accNo,@RequestBody LoanAccount lnAcc ) {

		int flag=ci.requestLoan(cust_id,accNo,lnAcc);
		return new ResponseEntity(flag, HttpStatus.OK);
	}
	
	
	
	    


	
}
