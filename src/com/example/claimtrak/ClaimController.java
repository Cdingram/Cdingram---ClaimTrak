package com.example.claimtrak;

import java.io.IOException;

public class ClaimController {
	
	private static ClaimList claimList = null;
	
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
	
	static public void saveClaimList() {
		try {
			ClaimListManager.getManager().saveClaimList(getClaimList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not save student List");
		}
	}
	
	public void addClaim(Claim claim) {
		getClaimList().addClaim(claim);
	}
}
