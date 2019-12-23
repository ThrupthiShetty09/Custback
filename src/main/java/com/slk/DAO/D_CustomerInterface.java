package com.slk.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.model.CustomerAccount;
import com.slk.model.D_Customer;
import com.slk.model.D_Loan;
import com.slk.model.D_Transaction;
import com.slk.model.LoanAccount;
import com.slk.model.TransferClass;

@Repository
public interface D_CustomerInterface {
	List<D_Customer> viewProfile(long id);
	List<D_Customer> viewAllCustomer();
	int transfer(double amount,TransferClass transAcc);
	List<D_Transaction> transactionhistory(int id);
	List<CustomerAccount> viewBalance(long id);
	public TransferClass checkTransfer(int id,CustomerAccount custAcc);
	List<LoanAccount> viewLoan(int id);
	public void updateprofile(long id,D_Customer cust);
	public int loginValidation(String username,String password);
	public int requestLoan(long cust_id,long accNo,LoanAccount lnAcc);
}
