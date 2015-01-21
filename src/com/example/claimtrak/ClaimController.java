package com.example.claimtrak;

import java.io.IOException;

public class ClaimController {
	
	private static ClaimList claimList = null;
	
	static public ClaimList getClaimList() {
		if (claimList == null) {
			//claimList = ClaimListManager.getManager().loadClaimList();
			claimList.addListener(new Listener() {

				@Override
				public void update() {
					// TODO Auto-generated method stub
					//saveClaimList();
				}
				
			});
		}
		return claimList;
	}
	
	static public void saveClaimList() {
	}
	
	public void addClaim(Claim claim) {
		getClaimList().addClaim(claim);
	}
}
