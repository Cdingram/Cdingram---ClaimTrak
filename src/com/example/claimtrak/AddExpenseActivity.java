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
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpenseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		
		ClaimListManager.initManager(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
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
	
	public void onStart() {
		super.onStart();
		//init currency array
		ArrayList<String> currencies = new ArrayList<String>();
		currencies.add("CAD");
		currencies.add("USD");
		currencies.add("EUR");
		currencies.add("GBP");
		// From developer.android.com/guide/topics/ui/controls/spinner.html
		final Spinner spinner = (Spinner) findViewById(R.id.addCurrencySpinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 GlobalClaim.spinner = (String) parent.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	@SuppressLint("SimpleDateFormat")
	public void addExpenseAction(View v) {
		Toast.makeText(this, "Adding An Expense", Toast.LENGTH_SHORT).show();
		// get text fields
		EditText expenseCat = (EditText) findViewById(R.id.addExpenseCategoryEditText);
		EditText expenseDate = (EditText) findViewById(R.id.addExpenseDateEditText);
		EditText expenseDes = (EditText) findViewById(R.id.addExpenseDescriptionEditText);
		EditText expenseAmount = (EditText) findViewById(R.id.addExpenseAmountEditText);
		
		// get all input
		String cat = expenseCat.getText().toString();
		String date = expenseDate.getText().toString();
		String des = expenseDes.getText().toString();
		String amount = expenseAmount.getText().toString();
		String currency = GlobalClaim.spinner;
		
		//checks 
		if (cat.length() == 0 || date.length() == 0 || des.length() == 0 || amount.length() == 0) {
			Toast.makeText(this, "Ensure all fields are filled", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (currency == null) {
			Toast.makeText(this, "Ensure currency is selected", Toast.LENGTH_SHORT).show();
			return;
		}
		//date check
		String dateFormat = "DD/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date dateD = null;
		try {
			dateD = format.parse(date);
		} catch (java.text.ParseException e) {
			// not valid date format error
			e.printStackTrace();
			Toast.makeText(this, "Ensure dates are in format dd/mm/yyyy", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// deal with expenses
		Expense expense = new Expense(dateD, cat, des);
		if(currency.equals("CAD")){
			expense.currency.addCad(amount);
		} else if (currency.equals("USD")) {
			expense.currency.addUSD(amount);
		} else if (currency.equals("EUR")) {
			expense.currency.addEUR(amount);
		} else if (currency.equals("GBP")) {
			expense.currency.addGBP(amount);
		}
		// add/save/clean
		GlobalClaim.claim.addExpense(expense);
		ClaimController.saveClaimList();
		GlobalClaim.spinner = null;
		GlobalClaim.expense = null;
	}
}
