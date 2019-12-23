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
	Long deleteAdmin(Long empId);
	a_Employee updateAdmin(Long empId,a_Employee emp);
	a_Employee updateSuperuser(Long empId,a_Employee emp);
	a_Employee updateCustomer(Long acc_no,a_Employee emp);
}


