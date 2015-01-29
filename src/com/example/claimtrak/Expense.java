package com.example.claimtrak;

public class Expense {
	
	protected String date;
	protected String category;
	protected String description;
	protected String amount;
	protected String currency;
	
	public Expense(String date, String category, String description, String amount, String currency) {
		this.date = date;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.currency = currency;
	}
	
	public Expense() {
		
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getAmount() {
		return this.amount;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
