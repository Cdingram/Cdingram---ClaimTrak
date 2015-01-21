package com.example.claimtrak;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ClaimListManager {

	static final String prefFile = "ClaimList";
	static final String slKey = "claimList";
	
	Context context;
	
	private static ClaimListManager claimListManager = null;

	public static void initManager(Context context) {
		if(claimListManager == null) {
			if(context == null) {
				throw new RuntimeException("Missing Context or claimListManager");
			}
			claimListManager = new ClaimListManager(context);
		}
	}
	
	public static ClaimListManager getManager() {
		if(claimListManager == null) {
			throw new RuntimeException("Did not initialize manager");
		}
		return claimListManager;
	}
	
	public ClaimListManager(Context context) {
		this.context = context;
	}
	
	
	/*public ClaimList loadClaimList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String claimListData = settings.getString(slKey, "");
		if (claimListData.equals("")) {
			return new ClaimList();
		} else {
			return claimListFromString(claimListData);
		}
	}
	
	
	private ClaimList claimListFromString(String claimListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(claimListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ClaimList) oi.readObject();
	}
	
	private String claimListToString(ClaimList cl) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(cl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
	
	public void saveClaimList(ClaimList cl) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slKey, claimListToString(cl));
		editor.commit();
	}
	*/
	
}
