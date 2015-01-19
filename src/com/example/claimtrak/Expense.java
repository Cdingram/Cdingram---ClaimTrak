package com.example.claimtrak;

import java.util.Date;

public class Expense {
	
	protected Date date;
	protected String category;
	protected String description;
	protected float amount;
	protected String currency;
	
	public Expense(Date date, String category, String description, float amount, String currency) {
		this.date = date;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.currency = currency;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public float getAmount() {
		return this.amount;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
