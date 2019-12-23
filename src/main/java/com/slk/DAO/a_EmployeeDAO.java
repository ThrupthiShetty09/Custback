package com.slk.DAO;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.model.a_Employee;

@Repository
public interface a_EmployeeDAO {
	a_Employee registerAdmin(a_Employee employee);
	List<a_Employee> getAllAdmin();
	List<a_Employee> getSuperuser();
	a_Employee get(Long empId);
	String deleteAdmin(String empId);
	a_Employee updateAdmin(String empId,a_Employee emp);
	a_Employee updateSuperuser(String empId,a_Employee emp);
	a_Employee updateCustomer(Long acc_no,a_Employee emp);
}


