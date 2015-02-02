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

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		
		ClaimListManager.initManager(this.getApplicationContext());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressLint("SimpleDateFormat")
	public void addClaimAction(View v) {	
		Toast.makeText(this, "Adding A Claim", Toast.LENGTH_SHORT).show();
		// get text fields
		EditText claimCat = (EditText) findViewById(R.id.claimCategoryEditText);
		EditText claimTo = (EditText) findViewById(R.id.claimToEditText);
		EditText claimFrom = (EditText) findViewById(R.id.claimFromEditText);
		ClaimController cc = new ClaimController();
		// get all input
		String cat = claimCat.getText().toString();
		String to = claimTo.getText().toString();
		String from = claimFrom.getText().toString();
		if (cat.length() == 0 || to.length() == 0 || from.length() == 0) {
			Toast.makeText(this, "Ensure all fields are filled", Toast.LENGTH_SHORT).show();
			return;
		}
		// checks
		String dateFormat = "DD/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date toD = null;
		Date fromD = null;
		try {
			toD = format.parse(to);
			fromD = format.parse(from);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "Ensure dates are in format dd/mm/yyyy", Toast.LENGTH_SHORT).show();
			return;
		}
		// add/save/clean
		cc.addClaim(new Claim(cat, toD, fromD, "In Progress"));
		ClaimController.saveClaimList();
		GlobalClaim.claim = null;
		
	}
}
