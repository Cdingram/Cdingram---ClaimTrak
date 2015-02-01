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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_expense);
		Toast.makeText(this, "Edit Expense", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_expense, menu);
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
		ArrayList<String> currencies = new ArrayList<String>();
		currencies.add("CAD");
		currencies.add("USD");
		currencies.add("EUR");
		currencies.add("GBP");
		// From developer.android.com/guide/topics/ui/controls/spinner.html
		final Spinner spinner = (Spinner) findViewById(R.id.editCurrencySpinner);
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
	public void editExpenseAction(View v) {
		Toast.makeText(this, "Editing Expense", Toast.LENGTH_SHORT).show();
		EditText expDate = (EditText) findViewById(R.id.editExpenseDateEditText);
		EditText expCat = (EditText) findViewById(R.id.editExpenseCategoryEditText);
		EditText expDes = (EditText) findViewById(R.id.editExpenseDescriptionEditText);
		EditText expAmount = (EditText) findViewById(R.id.editExpenseAmountEditText);
		//EditText expCurr = (EditText) findViewById(R.id.editExpense);
		
		String date = expDate.getText().toString();
		String cat = expCat.getText().toString();
		String des = expDes.getText().toString();
		String amount = expAmount.getText().toString();
		String currency = GlobalClaim.spinner;
		
		// add checking for proper things 
		Date dateD = null;
		if (date.length() != 0) {
			String dateFormat = "DD/MM/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			
			try {
				dateD = format.parse(date);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "Ensure dates are in format dd/mm/yyyy", Toast.LENGTH_SHORT).show();
				return;
			}
		}
		
		Expense expense = GlobalClaim.expense;
		
		if (date.length() != 0) {
			expense.setDate(dateD);
		}
		if (cat.length() != 0 ) {
			expense.setCategory(cat);
		}
		if (des.length() != 0) {
			expense.setDescription(des);
		}
		if (amount.length() != 0 && currency != null) {
			if(currency.equals("CAD")){
				expense.currency.addCad(amount);
			} else if (currency.equals("USD")) {
				expense.currency.addUSD(amount);
			} else if (currency.equals("EUR")) {
				expense.currency.addEUR(amount);
			} else if (currency.equals("GBP")) {
				expense.currency.addGBP(amount);
			}
		}
		
		ClaimController.saveClaimList();
		GlobalClaim.spinner = null;
		GlobalClaim.expense = null;
	}
}
