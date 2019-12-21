package com.slk.model;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class b_Customer {
	public Long cust_id;
	public String name;
	public String dob;
	public Long contact;
	public String address;
	public String username;
	public String password;
	public Long aadhar_card;
	public String pan_card;
	public String branch_id;
	public Long customer_Acc_no;
	public String open_date;
	public float balance;
	public String account_id;

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAadhar_card() {
		return aadhar_card;
	}

	public void setAadhar_card(Long aadhar_card) {
		this.aadhar_card = aadhar_card;
	}

	public String getPan_card() {
		return pan_card;
	}

	public void setPan_card(String pan_card) {
		this.pan_card = pan_card;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public Long getCustomer_Acc_no() {
		return customer_Acc_no;
	}

	public void setCustomer_Acc_no(Long customer_Acc_no) {
		this.customer_Acc_no = customer_Acc_no;
	}

	public String getOpen_date() {
		return open_date;
	}

	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

}
