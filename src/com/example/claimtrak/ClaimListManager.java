package com.example.claimtrak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;


public class ClaimListManager {

	private static final String FILENAME = "file.sav";
	
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
	
	
	public ClaimList loadClaimList() {
		Gson gson = new Gson();
		ClaimList claimList = new ClaimList();
		try {
			FileInputStream fis;
			InputStreamReader in = null;	
			fis = context.openFileInput(FILENAME);
			in = new InputStreamReader(fis);
				
		
			// Taken from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html
			Type typeOfT = new TypeToken<ClaimList>(){}.getType();
			claimList = gson.fromJson(in, typeOfT);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldnt Load claimList");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldnt Load claimList");
		}
		
		return claimList;
	}
	
	public void saveClaimList(ClaimList cl) throws IOException{
		Gson gson = new Gson();
		
		try {
			FileOutputStream fos = null;
		
			fos = context.openFileOutput(FILENAME, 0);//MODE_PRIVATE
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(cl, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
