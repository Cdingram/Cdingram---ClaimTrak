package com.example.claimtrak;

import java.util.ArrayList;
import java.util.Collection;

public class Claim {
	
	protected ArrayList<Expense> claim;
	
	public Claim(){
		claim = new ArrayList<Expense>();
	}
	
	public Collection<Expense> getClaim() {		
		return claim;
	}
}
