package com.slk.model;

public class CustomerAccount {
	private long customer_Acc_no; 
	private String open_date;
	private double balance;
	private long cust_id;
	private String account_id;
	public long getCustomer_Acc_no() {
		return customer_Acc_no;
	}
	public void setCustomer_Acc_no(long customer_Acc_no) {
		this.customer_Acc_no = customer_Acc_no;
	}
	public String getOpen_date() {
		return open_date;
	}
	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getCust_id() {
		return cust_id;
	}
	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	
	
}
