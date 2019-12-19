package com.slk.model;

public class Customer {
	public Long cust_id;
	public String name;
	public String dob;
	public long contact;
	public String address;
	public String username;
	public String password;
	public long aadhar_card;
	public String pan_card;
	public String branch_id;
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
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
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
	public long getAadhar_card() {
		return aadhar_card;
	}
	public void setAadhar_card(long aadhar_no) {
		this.aadhar_card = aadhar_no;
	}
	public String getPan_card() {
		return pan_card;
	}
	public void setPan_card(String pan_no) {
		this.pan_card = pan_no;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}


	

}
