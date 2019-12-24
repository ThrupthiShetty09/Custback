package com.slk.DAO;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.util.DButil;
import com.slk.model.CustomerAccount;
import com.slk.model.D_Customer;
import com.slk.model.D_Loan;
import com.slk.model.D_Transaction;
import com.slk.model.LoanAccount;
import com.slk.model.TransferClass;
@Repository
public class D_CustomerImpl implements D_CustomerInterface{
	static List<D_Customer> list1=new ArrayList();
	static List<LoanAccount> listLoan=new ArrayList();
	static List<D_Transaction> listTrans=new ArrayList();
	public D_CustomerImpl() {
		try{
			Connection conn=DButil.getConnection();
			Statement stmt = conn.createStatement(); 
			list1.clear();
	        ResultSet rs = stmt.executeQuery("SELECT customer_account.cust_id as cust_id,name,dob,contact,address,username,password,aadhar_number,pan_number,branch_id,customer_Acc_no FROM customer INNER JOIN customer_account ON customer.cust_id = customer_account.cust_id"); 
	        while (rs.next()) { 
	            D_Customer cust=new D_Customer();
	            cust.setCust_id(rs.getLong("cust_id"));
	            cust.setName(rs.getString("name"));
	            cust.setDob(rs.getString("DOB"));
	            cust.setContact(rs.getLong("contact"));
	            cust.setAddress(rs.getString("address"));
	            cust.setUsername(rs.getString("username"));
	            cust.setPassword(rs.getString("password"));
	            cust.setAdhar_number(rs.getLong("aadhar_number"));
	            cust.setPan_number(rs.getString("pan_number"));
	            cust.setBranch_id(rs.getString("branch_id"));
	            cust.setAccount_no(rs.getLong("customer_Acc_no"));
	            list1.add(cust);
	        } 
	       
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@Override
	public int loginValidation(String username,String password){
		int flag=0;
		for (D_Customer c:list1) {
		
			if (c.getUsername().equals(username)  && c.getPassword().equals(password)) {
				flag=1;
							
				return flag;
			}
		}
		return flag;
	}
	
	@Override
	public List<D_Customer> viewAllCustomer() {
		// TODO Auto-generated method stub
		return list1;
	}
	
	@Override
	public List<D_Customer> viewProfile(long id) {
		// TODO Auto-generated method stub
		List<D_Customer> lc=new ArrayList();
		for (D_Customer c:list1) {
			if (c.getCust_id()==id) {
				lc.add(c);
			}
		}
		return lc;
		
	}
	
	public void updateprofile(long id,D_Customer cust){
		for (D_Customer c:list1) {
			
			if (c.getCust_id()==id) {
				try{
					Connection conn=DButil.getConnection();
					Statement stmt = conn.createStatement(); 
			        stmt.executeUpdate("update customer set name='"+cust.getName()+"',DOB='"+cust.getDob()+"',contact="+cust.getContact()+",address='"+cust.getAddress()+"',username='"+cust.getUsername()+"',password='"+cust.getPassword()+"',aadhar_number="+cust.getAdhar_number()+",pan_number='"+cust.getPan_number()+"',branch_id='"+cust.getBranch_id()+"' where cust_id="+id+""); 
			        System.out.println("successfull updated......!");
				}catch(Exception e){
					System.out.println(e);
				}
				
				list1.set(list1.indexOf(c), cust);
			}
		}
		
	}
	
	@Override
	public List<CustomerAccount> viewBalance(long id) {
		List<CustomerAccount> listCustAcc=new ArrayList();
		listCustAcc.clear();
		// TODO Auto-generated method stub
		
		try{
			Connection conn=DButil.getConnection();
			Statement stmt = conn.createStatement(); 
			
	        ResultSet rs = stmt.executeQuery("SELECT balance,customer_Acc_no FROM customer INNER JOIN customer_account ON customer.cust_id = customer_account.cust_id where customer_account.cust_id="+id); 
	        while (rs.next()) { 
	        	CustomerAccount ca=new CustomerAccount();
	        	ca.setBalance(rs.getFloat("balance"));
	        	ca.setCustomer_Acc_no(rs.getLong("customer_Acc_no"));
	        	listCustAcc.add(ca);
	        } 
	       
			
		}catch(Exception e){
			System.out.println(e);
		}
		return listCustAcc;
	}

	@Override
	public TransferClass checkTransfer(int id,CustomerAccount custAcc){
		TransferClass tc=new TransferClass();
		long sendAccNo=id;
		
		long receiverAccNo=custAcc.getCustomer_Acc_no();
		double sendTempAmt=0;
		double receiveTempAmt=0;
		String accType="";

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Connection conn=DButil.getConnection();
			Statement stmt = conn.createStatement(); 
	        ResultSet rs = stmt.executeQuery("select cust_id,balance,customer_account.account_id,min_balance from customer_account,account where customer_account.account_id=account.account_id and customer_Acc_no="+sendAccNo); 
	        while (rs.next()) { 
	        	sendTempAmt=rs.getDouble("balance");
	        	accType=rs.getString("account_id");
	        	tc.setMinBalance(rs.getDouble("min_balance"));
	        	tc.setSenderAccount(sendAccNo);
	        	tc.setReceiverAccount(receiverAccNo);
	        	tc.setSenderAcctypeId(accType);
	        	tc.setSenderAmt(sendTempAmt);
	        	tc.setCust_id(rs.getLong("cust_id"));
	        	
	        }
	       rs = stmt.executeQuery("select balance from customer_account where customer_Acc_no="+receiverAccNo); 
	        while (rs.next()) { 
	        	receiveTempAmt=rs.getDouble("balance");
	        	tc.setReceiverAmt(receiveTempAmt);
	        }
		}catch(Exception e){
			System.out.println(e);
		}
		return tc;
		
	}
	
	
	@Override
	public int transfer(double amount,TransferClass transAcc) {
		// TODO Auto-generated method stub
		double tempAmt=0;
		String accType="";
		double debit=0;
		double credit=0;
		int transId=0;
		int flag=0;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(date));
		SimpleDateFormat ftime = new SimpleDateFormat("HH:mm:ss");
		System.out.println(ftime.format(date));
		try{
			Connection conn=DButil.getConnection();
			Statement stmt = conn.createStatement(); 
	        ResultSet rs = stmt.executeQuery("select max(trans_id) as id from transaction"); 
	        while (rs.next()) { 
	        	transId=rs.getInt("id");
	        }
	        transId++;
	        	tempAmt=transAcc.getSenderAmt()-amount;
	        		debit=amount;
	        		
	        		stmt.executeUpdate("update customer_account set balance="+tempAmt+" where customer_Acc_no="+transAcc.getSenderAccount());
	        		stmt.executeUpdate("insert into transaction values("+transId+",'"+formatter.format(date)+"','"+ftime.format(date)+"',"+debit+","+credit+","+tempAmt+","+transAcc.getSenderAccount()+","+transAcc.getCust_id()+")");
	        		debit=0;
	        		credit=amount;
	        		
	        		tempAmt=transAcc.getReceiverAmt()+amount;
	        		transId++;
	        		stmt.executeUpdate("update customer_account set balance="+tempAmt+" where customer_Acc_no="+transAcc.getReceiverAccount());
	        		stmt.executeUpdate("insert into transaction values("+transId+",'"+formatter.format(date)+"','"+ftime.format(date)+"',"+debit+","+credit+","+tempAmt+","+transAcc.getReceiverAccount()+","+transAcc.getCust_id()+")");
	        		flag=1;
	      
	       
	        }
	     	catch(Exception e){
			System.out.println(e);
		}
		
		return flag;
	}

	@Override
	public List<D_Transaction> transactionhistory(int id) {
		listTrans.clear();
		try{
			Connection conn=DButil.getConnection();
			Statement stmt = conn.createStatement(); 
	       ResultSet rs = stmt.executeQuery("select * from TRANSACTION where account_no="+id); 
	        while (rs.next()) {
	        	D_Transaction tl=new D_Transaction();
	        	tl.setTrans_id(rs.getString("trans_id"));
	        	tl.setTrans_date(rs.getString("date"));
	        	tl.setTrans_time(rs.getString("time"));
	        	tl.setDebit(rs.getDouble("debit"));
	        	tl.setCredit(rs.getDouble("credit"));
	        	tl.setBalance(rs.getDouble("balance"));
	        	tl.setAccount_no(rs.getString("account_no"));
	        	tl.setCust_id(rs.getLong("cust_id"));
	        	listTrans.add(tl);
	        }
		}catch(Exception e){
			System.out.println(e);
		}
			
				return listTrans;
	}

	@Override
	public List<LoanAccount> viewLoan(int id) {
		try{
			Connection conn=DButil.getConnection();
			Statement stmt = conn.createStatement(); 
			listLoan.clear();
	        ResultSet rs=stmt.executeQuery("SELECT account_no,balance,loan_type,interest_rate,open_date,close_date FROM loan INNER JOIN loanaccount ON loan.loan_id = loanaccount.loan_id where account_no="+id); 
	        while(rs.next()){
	        	LoanAccount lnAcc=new LoanAccount();
	        	
	        	lnAcc.setOpen_date(rs.getString("open_date"));
	        	lnAcc.setClose_date("close_date");
	        	lnAcc.setBalance(rs.getDouble("balance"));
	        	lnAcc.setInterest_rate(rs.getDouble("interest_rate"));
	        	lnAcc.setLoan_id(rs.getString("loan_type"));
	        	lnAcc.setAccount_no(rs.getLong("account_no"));
	        	listLoan.add(lnAcc);
	        }
		}catch(Exception e){
			System.out.println(e);
		}
		return listLoan;
	}
	
	public int requestLoan(long cust_id,long accNo,LoanAccount lnAcc){
		int id=0;
		int flag=0;
		String str="";
		try{
			Connection conn=DButil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select max(loan_Acc_no) as id from loanaccount"); 
	        while (rs.next()){ 
	        	str= rs.getString("id");
	        	id=Integer.parseInt(str.substring(2));
	        	
	        }
	        id++;
	        str="LO0";
	        String temp=str+id;
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        Date date = new Date();
	        int approval=0;
	        int res=stmt.executeUpdate("insert into loanaccount values('"+temp+"','"+dateFormat.format(date)+"','"+lnAcc.getClose_date()+"',"+lnAcc.getBalance()+",'"+lnAcc.getLoan_id()+"','"+lnAcc.getBranch_id()+"',"+cust_id+","+accNo+","+approval+")"); 
	        if(res>0){
	        	flag=1;
	        }else{
	        	flag=0;
	        }
		}catch(Exception e){
			System.out.println(e);
		}
		return flag;
	}

	


	
}
