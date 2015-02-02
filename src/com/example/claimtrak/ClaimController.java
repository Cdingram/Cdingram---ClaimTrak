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

// controller heavily inspired by Abram Hindle's student picker tutorials

package com.example.claimtrak;

import java.io.IOException;
/*
 * This class is a controller for claims. It has a claimslist which is managed by the manager and allows
 * for manipulation of the claimList object including saving and adding to the list
 */
public class ClaimController {
	
	private static ClaimList claimList = null;
	// get claimList
	static public ClaimList getClaimList() {
		if (claimList == null) {
			claimList = ClaimListManager.getManager().loadClaimList();
			if (claimList == null) {
				claimList = new ClaimList();
			}
			claimList.addListener(new Listener() {

				@Override
				public void update() {
					saveClaimList();
				}
			
			});
		}
		return claimList;
	}
	// save list 
	static public void saveClaimList() {
		try {
			ClaimListManager.getManager().saveClaimList(getClaimList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not save student List");
		}
	}
	// add claim
	public void addClaim(Claim claim) {
		getClaimList().addClaim(claim);
	}
}
