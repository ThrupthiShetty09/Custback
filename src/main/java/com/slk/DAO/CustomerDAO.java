package com.slk.DAO;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.slk.model.Customer;
@RestController
public interface CustomerDAO {
	public List<Customer> getAllCustomers() throws Exception;
	 public void updateCustomer(Customer customer);

}
