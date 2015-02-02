/*
 * Copyright Cody Ingram 2015
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * For full license details and acknowledgements, please refer to the README-LICENSE file
 * 
 * github.com/Cdingram/Cdingram-ClaimTrak
*/
package com.example.claimtrak;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Claim {
	// attributes
	protected ArrayList<Expense> expenses = null;
	protected String category;
	protected Date toDate;
	protected Date fromDate;
	protected String status;
	//constructors
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
	// get total currency amounts from expenses within a claim
	public String getTotalCAD() {
		float total = 0;
		for (Expense item: expenses) {
			total += Float.valueOf(item.currency.getCAD());
		}
		return ""+total;
		
	}
	
	public String getTotalUSD() {
		float total = 0;
		for (Expense item: expenses) {
			total += Float.valueOf(item.currency.getUSD());
		}
		return ""+total;
		
	}
	
	public String getTotalEUR() {
		float total = 0;
		for (Expense item: expenses) {
			total += Float.valueOf(item.currency.getEUR());
		}
		return ""+total;
		
	}
	
	public String getTotalGBP() {
		float total = 0;
		for (Expense item: expenses) {
			total += Float.valueOf(item.currency.getGBP());
		}
		return ""+total;
		
	}
	// getters/setters/adders
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
	
	public String getStatus() {
		return this.status;
	}
	

}
