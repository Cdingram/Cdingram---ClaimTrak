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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EmailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email, menu);
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
	
	public void send(View v) {
		// from stackoverflow.com/questions/8284706/send-email-via-gmail
		EditText email = (EditText) findViewById(R.id.editText1);
		Intent send = new Intent(Intent.ACTION_SENDTO);
		String expenseString = ""+GlobalClaim.claim.getClaimName();
		for (Expense item: GlobalClaim.claim.getExpenses()) {
			expenseString = expenseString + "\n" + item.getCategory() + ":" + "\n" + item.getDescription() + "\n " + item.getDate() + "\n" + "Totals" + "\n" + item.currency.getCAD() + " CAD" +"\n" +  item.currency.getUSD() + " USD" + "\n" + item.currency.getEUR() + " EUR" + "\n" +  item.currency.getGBP() + " GBP" + "\n";
		}
		String uriText = "mailto:" + Uri.encode(email.getText().toString()) + "?subject=" + Uri.encode(GlobalClaim.claim.getClaimName()) + "&body=" + Uri.encode(expenseString);
		Uri uri = Uri.parse(uriText);
		send.setData(uri);
		startActivity(Intent.createChooser(send, "Send to: "));
	}
}
