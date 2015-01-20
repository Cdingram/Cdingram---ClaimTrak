package com.example.claimtrak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Claim implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1512757844717363369L;
	protected ArrayList<Expense> expenses;
	protected String category;
	protected String toDate;
	protected String fromDate;
	protected String status;
	
	public Claim(String category, String toDate, String fromDate, String status){
		this.expenses = new ArrayList<Expense>();
		this.category = category;
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.status = status;
		
	}
	
	public Collection<Expense> getExpenses() {		
		return this.expenses;
	}
	
	/*
	public int hashCode() {
		return ("Claim" + )
	}
	*/
	
}
