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
