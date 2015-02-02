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
		// get expense
		Expense expense = GlobalClaim.expense;
		//get textViews
		TextView catText = (TextView) findViewById(R.id.categorySummaryTextView);
		TextView dateText = (TextView) findViewById(R.id.dateSummaryTextView);
		TextView desText = (TextView) findViewById(R.id.desSummaryTextView);
		TextView amountText = (TextView) findViewById(R.id.amountSummaryTextView);
		// set text for textviews to display expense info
		catText.setText(expense.getCategory());
		dateText.setText(expense.getDate().toString());
		desText.setText(expense.getDescription());
		amountText.setText("Total" + "\n" + expense.currency.getCAD() + " CAD" +"\n" +  expense.currency.getUSD() + " USD" + "\n" + expense.currency.getEUR() + " EUR" + "\n" +  expense.currency.getGBP() + " GBP");
		
		GlobalClaim.expense = null;
	}
}
