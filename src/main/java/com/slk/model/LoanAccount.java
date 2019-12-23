package com.slk.model;

public class LoanAccount {
	private double issue_amount;
	private String loan_Acc_no;
	private String open_date;
	private String close_date;
	private double balance;
	private String loan_id;
	private String branch_id;
	private long cust_id;
	private long account_no;
	private int approval;
	private double interest_rate;
	
	
	public double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}
	public double getIssue_amount() {
		return issue_amount;
	}
	public void setIssue_amount(double issue_amount) {
		this.issue_amount = issue_amount;
	}
	public String getLoan_Acc_no() {
		return loan_Acc_no;
	}
	public void setLoan_Acc_no(String loan_Acc_no) {
		this.loan_Acc_no = loan_Acc_no;
	}
	public String getOpen_date() {
		return open_date;
	}
	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}
	public String getClose_date() {
		return close_date;
	}
	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public long getCust_id() {
		return cust_id;
	}
	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}
	public long getAccount_no() {
		return account_no;
	}
	public void setAccount_no(long account_no) {
		this.account_no = account_no;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
}
