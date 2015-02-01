package com.example.claimtrak;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Claim {
	
	protected ArrayList<Expense> expenses = null;
	protected String category;
	protected Date toDate;
	protected Date fromDate;
	protected String status;
	
	public Claim() {
		this.expenses = new ArrayList<Expense>();
	}
	
	public Claim(String category, Date toDate, Date fromDate, String status){
		this.expenses = new ArrayList<Expense>();
		this.category = category;
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.status = status;
	}
	
	public String getClaimName() {
		return this.category;
	}
	
	public void addExpense(Expense expense) {
		expenses.add(expense);
	}
	
	public void removeExpense(Expense expense) {
		expenses.remove(expense);
	}
	
	public Collection<Expense> getExpenses() {	
		if (expenses == null) {
			expenses = new ArrayList<Expense>();
		}
		return this.expenses;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	

}
