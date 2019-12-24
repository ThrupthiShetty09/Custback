package com.slk.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.util.DButil;
import com.slk.model.Branch;

@Repository
public class BranchDAOImpl implements BranchDAO{

	Connection connection = null;
	private static List<Branch> branches;
	
	{
		connection = DButil.getConnection();
		System.out.println(connection);
	}

	@Override
	public Branch registerBranch(Branch branch) {
		// TODO Auto-generated method stub
		
		String select_query = "Select max(branch_id) from branch";
		Statement select_stmt;
		int i=0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			//select_rs.next();
			String id = "ABC0";
			String br_Id = select_rs.getString(1);
			int temp_id = Integer.parseInt(br_Id.substring(5));
			int temp_brId = ++temp_id;
			String eid = id+temp_brId;
			//int branch_Id = select_rs.getInt(1);
			//int temp_brId = ++branch_Id;
			/*employee.setEmpId(temp_empId);
			employee.setName("Soumya");
			employee.setDob("1997-07-25");
			employee.setContact(876260339);
			employee.setUsername("sou");
			employee.setPwd("sou23");
			employee.setRole(role);*/
			PreparedStatement pst = connection.prepareStatement("Insert into Branch values(?,?,?,?,?)");
			
			pst.setString(1,eid);
			pst.setString(2,branch.getBr_name());
			pst.setString(3,branch.getBr_ifsc());
			pst.setString(4,branch.getBr_addr());
			pst.setLong(5,branch.getBr_contact());
			
			i = pst.executeUpdate();
			System.out.println(i + " records inserted");
			//admins.add(employee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return branch;
		//return null;
	}

	@Override
	public List<Branch> getAllBranch() {
		// TODO Auto-generated method stub
		String query = "Select * from branch";
		
		branches = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Branch branch = new Branch();
				String brId = rs.getString(1);
				String brName = rs.getString(2);
				String brIfsc = rs.getString(3);
				String brAddr = rs.getString(4);
				long brContact = rs.getLong(5);
				
				
				branch.setBr_id(brId);
				branch.setBr_name(brName);
				branch.setBr_ifsc(brIfsc);
				branch.setBr_contact(brContact);
				branch.setBr_addr(brAddr);
			
				//admins.add(new Employee(empId,empName,empDob,empContact));
				branches.add(branch);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return branches;
		
	}

	@Override
	public Branch updateBranch(String branchId, Branch branch) {
		// TODO Auto-generated method stub
		try {
			String updSql = "UPDATE  Branch set branch_address = ?,branch_contact = ? WHERE branch_id = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			pst.setString(1, branch.getBr_addr());
			pst.setLong(2, branch.getBr_contact());
			pst.setString(3, branchId);
			
			
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Branch updated");
			}
			return branch;
		
		} catch (SQLException e)
		{
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public String deleteBranch(String branchId) {
		// TODO Auto-generated method stub
		try{
			
			String sql="DELETE FROM branch WHERE branch_id = ? ";			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, branchId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Branch Deleted");
			}
			
			return branchId;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
