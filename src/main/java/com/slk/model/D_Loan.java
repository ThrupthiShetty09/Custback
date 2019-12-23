package com.slk.model;

public class D_Loan {
	
	private String loan_id;
	private String loan_type;
	private double interest_rate;
	private String description;
	
	public String getId() {
		return loan_id;
	}
	public void setId(String id) {
		this.loan_id = id;
	}
	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}
	
}
