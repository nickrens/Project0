package dev.rens.entities;

import java.text.DecimalFormat;

public class Account {

	private String accountID;
	private String name;
	private double balance;
	private String userID;
	
	public Account() {
		super();
	}

	public Account(String name) {
		super();
		this.accountID = null;
		this.name = name;
		this.balance = 0;
		this.userID = null;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#0.00");
		return "Name: " + name + ", Balance: $" + df.format(balance) ;
	}
}
