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
import com.slk.model.Loan;


@Repository
public class LaonDAOImpl implements LoanDAO {
	
	Connection connection = null;
	private static List<Loan> loans;
	
	{
		connection = DButil.getConnection();
		System.out.println(connection);
	}

	@Override
	public Loan addLoan(Loan loan) {
		// TODO Auto-generated method stub
		String select_query = "Select max(loan_id) from loan";
		Statement select_stmt;
		int i=0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			String loanid = "LO0";
			String loan_Id = select_rs.getString(1);
			int temp_id = Integer.parseInt(loan_Id.substring(2));
			int temp_loanId = ++temp_id;
			String id = loanid+temp_loanId;
			/*employee.setEmpId(temp_empId);
			employee.setName("Soumya");
			employee.setDob("1997-07-25");
			employee.setContact(876260339);
			employee.setUsername("sou");
			employee.setPwd("sou23");
			employee.setRole(role);*/
			PreparedStatement pst = connection.prepareStatement("Insert into Loan values(?,?,?,?)");
			
			pst.setString(1,id);
			pst.setString(2,loan.getLoan_type());
			pst.setString(3,loan.getLoan_irate());
			pst.setString(4,loan.getLoan_desc());
		
			
			i = pst.executeUpdate();
			System.out.println(i + " records inserted");
			//admins.add(employee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return loan;
		
	
	}

	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		String query = "Select * from loan";
		
		loans = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Loan loan = new Loan();
				String loan_Id = rs.getString(1);
				String loan_type = rs.getString(2);
				String loan_rate = rs.getString(3);
				String loan_desc = rs.getString(4);
				
				loan.setLoan_id(loan_Id);
				loan.setLoan_type(loan_type);
				loan.setLoan_irate(loan_rate);
				loan.setLoan_desc(loan_desc);
				loans.add(loan);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loans;
		
	
	}

	@Override
	public Loan updateLoan(String loanId, Loan loan) {
		// TODO Auto-generated method stub
		try {
			String updSql = "UPDATE  Loan set loan_type = ?, interest_rate = ?, loan_desc = ? WHERE loan_id = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			pst.setString(1, loan.getLoan_type());
			pst.setString(2, loan.getLoan_irate());
			pst.setString(3, loan.getLoan_desc());
			pst.setString(4, loanId);
			
			
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Loan updated");
			}
			return loan;
		
		} catch (SQLException e)
		{
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public String deleteLoan(String loanId) {
		// TODO Auto-generated method stub
		try{
			
			String sql="DELETE FROM loan WHERE loanId = ? ";			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, loanId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Loan Deleted");
			}
			
			return loanId;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
		

	@Override
	public List<Loan> get(String l_type) {
		// TODO Auto-generated method stub
		String query = "Select * from loan where loan_type = l_type";
		
		loans = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Loan loan = new Loan();
				String loan_Id = rs.getString(1);
				String loan_type = rs.getString(2);
				String loan_rate = rs.getString(3);
				String loan_desc = rs.getString(4);
				
				loan.setLoan_id(loan_Id);
				loan.setLoan_type(loan_type);
				loan.setLoan_irate(loan_rate);
				loan.setLoan_desc(loan_desc);
				loans.add(loan);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loans;
		
	}

}