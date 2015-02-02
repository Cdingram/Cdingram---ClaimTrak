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

public class ClaimList {
	
	protected ArrayList<Claim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ClaimList() {
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	
	public Collection<Claim> getClaims() {
		return claimList;
	}
	
	public void addClaim(Claim testClaim) {
		claimList.add(testClaim);
		notifyListeners();
	}
	
	
	private void notifyListeners() {
		for (Listener listener: getListeners()) {
			listener.update();
		}
	}
	
	public void removeClaim(Claim testClaim) {
		claimList.remove(testClaim);
		notifyListeners();
	}
	
	public int size() {
		return claimList.size();
	}
	
	public boolean contains(Claim testClaim) {
		return claimList.contains(testClaim);
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
	
}
