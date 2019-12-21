package com.slk.controller;

import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RestController;
import com.slk.DAO.*;
import com.slk.model.*;
import com.slk.util.*;

import ch.qos.logback.core.joran.action.NewRuleAction;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeerestController {
	@Autowired
	private EmployeeDAO employeeDAO;

	@GetMapping("/B_Employee")
	public List getallManagers() throws Exception {
		System.out.println("the functiuon inside controller");
		return employeeDAO.getallManagers();
	}
	
	@GetMapping("/B_MD/{username}/{password}")
	public ResponseEntity getAgentById(@PathVariable("username") String username ,@PathVariable("password") String password) throws Exception {
		boolean ad = employeeDAO.login(username,password);
		System.out.println(username+" "+password);
		return new ResponseEntity(ad, HttpStatus.OK);

	}

	@PostMapping("/post/B_Employee")
	public ResponseEntity addManager(@RequestBody Employee employee) {
		System.out.println("function inside the controller");
		employeeDAO.addManager(employee);
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@DeleteMapping("/delete/B_Employee/{employee_id}")
	public ResponseEntity deleteManager(@PathVariable String employee_id) {
		employeeDAO.deleteManager(employee_id);
		return new ResponseEntity(employee_id, HttpStatus.OK);
	}

	@GetMapping("/B_Employee/{employee_id}")
	public ResponseEntity getMDbyid(@PathVariable String employee_id) {
		// System.out.println("****");
		Employee emp = employeeDAO.getMDbyid(employee_id);
		employeeDAO.getMDbyid(employee_id);
		return new ResponseEntity(emp, HttpStatus.OK);
	}

	@PutMapping("/put/MD/B_Employee/{employee_id}")
	public ResponseEntity updateMD(@PathVariable String employee_id,@RequestBody Employee emp) {
		employeeDAO.updateMD(employee_id, emp);
		return new ResponseEntity(emp, HttpStatus.OK);
	}

}
