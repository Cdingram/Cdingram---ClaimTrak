package com.example.claimtrak;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ExpenseSummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_summary);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_summary, menu);
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
	
	@Override
	public void onStart() {
		super.onStart();
		Expense expense = GlobalClaim.expense;
		
		TextView catText = (TextView) findViewById(R.id.categorySummaryTextView);
		TextView dateText = (TextView) findViewById(R.id.dateSummaryTextView);
		TextView desText = (TextView) findViewById(R.id.desSummaryTextView);
		TextView amountText = (TextView) findViewById(R.id.amountSummaryTextView);
		
		catText.setText(expense.getCategory());
		dateText.setText(expense.getDate().toString());
		desText.setText(expense.getDescription());
		amountText.setText("Total" + "\n" + expense.currency.getCAD() + " CAD" +"\n" +  expense.currency.getUSD() + " USD" + "\n" + expense.currency.getEUR() + " EUR" + "\n" +  expense.currency.getGBP() + " GBP");
		
		GlobalClaim.expense = null;
	}
}
