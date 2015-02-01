package com.example.claimtrak;

public class Currency {
	private float CAD = 0;
	private float USD = 0;
	private float EUR = 0;
	private float GBP = 0;
	
	Currency() {
		
	}
	
	public void wipe() {
		CAD = 0;
		USD = 0;
		EUR = 0;
		GBP = 0;
	}
	
	public String getCAD() {
		return "" + this.CAD;
	}
	
	public void addCad(String cad) {
		this.CAD = Float.valueOf(cad);
	}
	
	public String getUSD() {
		return "" + this.USD;
	}
	
	public void addUSD(String usd) {
		this.USD = Float.valueOf(usd);
	}
	
	public String getEUR() {
		return "" + this.EUR;
	}
	
	public void addEUR(String eur) {
		this.EUR = Float.valueOf(eur);
	}
	
	public String getGBP() {
		return "" + this.GBP;
	}
	
	public void addGBP(String gbp) {
		this.GBP = Float.valueOf(gbp);
	}
}
