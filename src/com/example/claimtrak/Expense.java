package com.example.claimtrak;

import java.util.Date;

public class Expense {
	
	protected Date date;
	protected String category;
	protected String description;
	public Currency currency = new Currency();
	
	public Expense(Date date, String category, String description) {
		this.date = date;
		this.category = category;
		this.description = description;
	}
	
	public Expense() {
		
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
