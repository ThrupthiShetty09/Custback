package com.slk.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;
import com.slk.model.Customer;
import com.slk.util.DButil;
@RestController
public class CustomerDAOimpl implements CustomerDAO {
	Connection connection = DButil.getConnection();

	public CustomerDAOimpl() {
		connection = DButil.getConnection();
		System.out.println("connection" + connection);
	}
	//@Override
			public List<Customer> getAllCustomers() throws Exception {
				// TODO Auto-generated method stub
				List<Customer> customer = new ArrayList<Customer>();
				System.out.println("hfcgf");
				try {

					PreparedStatement stmt = connection.prepareStatement("select * from customer");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
                         Customer cst=new Customer();
                     	cst.setCust_id(rs.getString(1));
                     	System.out.println(rs.getInt(1));
                     	cst.setName(rs.getString(2));
                     	cst.setDob(rs.getString(3));
                     	cst.setContact(rs.getLong(4));
                     	cst.setAddress(rs.getString(5));
                     	cst.setUsername(rs.getString(6));
                     	cst.setPassword(rs.getString(7));
                     	cst.setAadhar_no(rs.getLong(8));
                     	cst.setPan_no(rs.getString(9));
                     	cst.setBranch_id(rs.getString(10));
   
						customer.add(cst);
						System.out.println(customer);
					}
				}

				catch (Exception e) {
					e.printStackTrace();
				}
				return customer;
			}
		

public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
Statement stmt ;
	String query="select max(cust_id) from customer";
		try {
			stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			int ab=rs.getInt(1);
			ab=ab+1;
/*			String sql ="Insert into customer values(?,?,?,?,?,?,?,?,?,?)"; 
*/			java.sql.PreparedStatement pst = connection.prepareStatement("Insert into customer values(?,?,?,?,?,?,?,?,?,?)");
            pst.setLong(1,ab);
			pst.setString(2, customer.getName());
			pst.setString(3, customer.getDob());
			pst.setLong(4, customer.getContact());
			pst.setString(5, customer.getAddress());
			pst.setString(6, customer.getUsername());
			pst.setString(7, customer.getPassword());
			pst.setLong(8, customer.getAadhar_no());
			pst.setString(9, customer.getPan_no());
			pst.setString(10, customer.getBranch_id());
			
			System.out.println("Customer Updated");

			int res = pst.executeUpdate();
			
               
			if (res > 0) {
				System.out.println("Customer Updated");

			}
	

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
