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
			//e.printStackTrace();
			//throw new RuntimeException("Couldnt Load claimList");
			
			try {
				FileOutputStream fos = context.openFileOutput(FILENAME, 0);
				fos.close();
				claimList = ClaimListManager.getManager().loadClaimList();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
