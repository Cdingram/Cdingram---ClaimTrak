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

public class EditClaim extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
		Toast.makeText(this, "Edit Claim", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
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
	public void editClaimAction(View v) {
		Toast.makeText(this, "Editing Claim", Toast.LENGTH_SHORT).show();
		// get edit text fields
		EditText claimCat = (EditText) findViewById(R.id.editClaimCategoryEditText);
		EditText claimTo = (EditText) findViewById(R.id.editClaimToEditText);
		EditText claimFrom = (EditText) findViewById(R.id.editClaimFromEditText);
		EditText claimStat = (EditText) findViewById(R.id.editClaimStatEditText);
		// get user inputs
		String cat = claimCat.getText().toString();
		String to = claimTo.getText().toString();
		String from = claimFrom.getText().toString();
		String stat = claimStat.getText().toString();
		
		//checking for proper date/status/etc
		Date toD = null;
		Date fromD = null;
		if (to.length() != 0 || from.length() != 0) {
			String dateFormat = "DD/MM/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			
			try {
				toD = format.parse(to);
				fromD = format.parse(from);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "Ensure dates are in format dd/mm/yyyy", Toast.LENGTH_SHORT).show();
				return;
			}
		}	
		Claim claim = GlobalClaim.claim;
		
		if (cat.length() != 0 ) {
			claim.setCategory(cat);
		}
		if (to.length() != 0 ) {
			claim.setToDate(toD);
		}
		if (from.length() != 0 ) {
			claim.setToDate(fromD);
		}
		if (stat.length() != 0 ) {
			if (stat.equals("Submitted") || stat.equals("Returned") || stat.equals("Approved")){
				claim.setStatus(stat);
			} else {
				Toast.makeText(this, "Ensure status is Submitted or Returned or Approved", Toast.LENGTH_SHORT).show();
			}
		}
		// save and clean
		ClaimController.saveClaimList();
		GlobalClaim.claim = null;
	}
}
