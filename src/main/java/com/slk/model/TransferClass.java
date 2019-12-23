package com.slk.model;

public class TransferClass {
	private long senderAccount;
	private long receiverAccount;
	private double senderAmt;
	private double receiverAmt;
	private double minBalance;
	private String senderAcctypeId;
	private long cust_id;
	
	
	public long getCust_id() {
		return cust_id;
	}
	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}
	public double getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	public long getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(long senderAccount) {
		this.senderAccount = senderAccount;
	}
	public long getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(long receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	public double getSenderAmt() {
		return senderAmt;
	}
	public void setSenderAmt(double senderAmt) {
		this.senderAmt = senderAmt;
	}
	public double getReceiverAmt() {
		return receiverAmt;
	}
	public void setReceiverAmt(double receiverAmt) {
		this.receiverAmt = receiverAmt;
	}
	public String getSenderAcctypeId() {
		return senderAcctypeId;
	}
	public void setSenderAcctypeId(String senderAcctypeId) {
		this.senderAcctypeId = senderAcctypeId;
	}
	
}
