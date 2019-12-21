package com.slk.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.slk.model.Employee;
import com.slk.util.*;

@RestController
public class EmployeeDAOimpl implements EmployeeDAO {
	// Connection connection = null;
	Connection connection = DButil.getConnection();

	public EmployeeDAOimpl() {
		connection = DButil.getConnection();
		System.out.println("connection" + connection);
	}

	@Override
	public List<Employee> getallManagers() throws Exception {
		List<Employee> emp = new ArrayList();
		try {
			System.out.println("function inside list");

			PreparedStatement stmt = connection.prepareStatement(
					"select distinct employee.employee_id,employee_name,dob,contact,mail,employee_role,branch_id from employee,employee_branch where employee.employee_id=employee_branch.employee_id;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployee_id(rs.getString(1));
				employee.setEmployee_name(rs.getString(2));
				employee.setDob(rs.getString(3));
				employee.setContact(rs.getLong(4));
				employee.setMail(rs.getString(5));
				employee.setEmployee_role(rs.getString(6));
				employee.setBranch_id(rs.getString(7));

				emp.add(employee);
				// System.out.println("testing Tomcat");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public void addManager(Employee emp) {
		try {
			/*
			 * String query1="select max(employee_id) from employee";
			 * PreparedStatement stmt1=connection.prepareStatement(query1);
			 * ResultSet rs1= stmt1.executeQuery(query1); rs1.next();
			 * 
			 * int employee_id=rs1.getInt(1); System.out.println(
			 * "before increment"); employee_id=++employee_id;
			 * System.out.println("aftr increment");
			 */
			String query1 = " insert into employee values(?,?,?,?,?,?,?,?); ";
			PreparedStatement stmt1 = connection.prepareStatement(query1);
			System.out.println("function incoming");

			stmt1.setString(1, emp.getEmployee_id());
			stmt1.setString(2, emp.getEmployee_name());
			stmt1.setString(3, emp.getDob());
			stmt1.setLong(4, emp.getContact());
			stmt1.setString(5, emp.getMail());
			stmt1.setString(6, emp.getUsername());
			stmt1.setString(7, emp.getPassword());
			stmt1.setString(8, emp.getEmployee_role());
			System.out.println(emp.username + emp.password + emp.getEmployee_role());
			int j = stmt1.executeUpdate();
			System.out.println(j);
			System.out.println("insertion to the employee done");

			String query2 = "insert into employee_branch values(?,?);";
			PreparedStatement stmt2 = connection.prepareStatement(query2);
			System.out.println("fucntion to add into branch");

			String id = emp.employee_id;
			stmt2.setString(1, id);
			stmt2.setString(2, emp.getBranch_id());
			int i = stmt2.executeUpdate();
			System.out.println(i);

			/*
			 * String id= emp.employee_id; stmt.setString(9,id);
			 * System.out.println(id); stmt.setString(10, emp.getBranch_id());
			 * System.out.println(emp.branch_id); ResultSet i=
			 * stmt.executeQuery(); System.out.println("i="+i);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String deleteManager(String employee_id) {
		try {
			Employee emp = new Employee();
			String query = "delete  employee,employee_branch from employee inner join employee_branch where employee.employee_id=employee_branch.employee_id && employee.employee_id=?;";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, employee_id);
			int res = stmt.executeUpdate();
			System.out.println("res=" + res);
			if (res > 0) {
				System.out.println("deletion happened");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee_id;
	}

	@Override
	public Employee getMDbyid(String employee_id) {
		Employee emp = null;
		try {

			String query = "select * from employee  where employee_role='managing director' && employee_id=?;";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, employee_id);
			ResultSet rs = stmt.executeQuery();
			System.out.println(employee_id);
			while (rs.next()) {
				emp = new Employee();
				emp.setEmployee_id(rs.getString(1));
				emp.setEmployee_name(rs.getString(2));
				emp.setDob(rs.getString(3));
				emp.setContact(rs.getLong(4));
				emp.setMail(rs.getString(5));
				emp.setUsername(rs.getString(6));
				emp.setPassword(rs.getString(7));
				emp.setEmployee_role(rs.getString(8));
				System.out.println(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public Employee updateMD(String employee_id,Employee emp) {
		System.out.println(employee_id);
		try {
			// emp=new Employee();
			String query = "update employee set employee_name=?,dob=?,contact=?,mail=?,username=?,password=?,employee_role=? where employee_id=? && employee_role='managing director';";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, emp.getEmployee_name());
			stmt.setString(2, emp.getDob());
			stmt.setLong(3, emp.getContact());
			stmt.setString(4, emp.getMail());
			stmt.setString(5, emp.getUsername());
			stmt.setString(6, emp.getPassword());
			stmt.setString(7, emp.getEmployee_role());
			stmt.setString(8, employee_id);
			int res = stmt.executeUpdate();
			if (res > 0) {
				System.out.println("updatation completed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public boolean login(String username, String password) throws Exception {
	
	Employee emp = new Employee();
	boolean flag = false;
	PreparedStatement stmt = connection.prepareStatement("select username,password from employee where username=?  and password=? ");
	stmt.setString(1, username);
	stmt.setString(2, password);
	ResultSet rs = stmt.executeQuery();
	while (rs.next()) {
		emp.setUsername(rs.getString(1));
		emp.setPassword(rs.getString(2));
		flag = true;
	}
	return flag;
}

	
}
