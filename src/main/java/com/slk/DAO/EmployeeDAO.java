package com.slk.DAO;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import com.slk.model.Employee;

@RestController
public interface EmployeeDAO {
	public List<Employee> getallManagers() throws Exception;
	public void addManager(Employee emp);
	public String deleteManager(String employee_id);
	public Employee getMDbyid(String employee_id );
	public Employee updateMD(String employee_id, Employee emp);
	public boolean login(String username, String password) throws Exception ;
}
