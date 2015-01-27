package com.example.claimtrak;


import java.util.ArrayList;
import java.util.Collection;

public class Claim {
	
	protected ArrayList<Expense> expenses;
	protected String category;
	protected String toDate;
	protected String fromDate;
	protected String status;
	
	public Claim() {
		this.expenses = new ArrayList<Expense>();
	}
	
	public Claim(String category, String toDate, String fromDate, String status){
		this.expenses = new ArrayList<Expense>();
		this.category = category;
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.status = status;
		
	}
	
	public String getClaimName() {
		return this.category;
	}
	
	public Collection<Expense> getExpenses() {		
		return this.expenses;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	

}
