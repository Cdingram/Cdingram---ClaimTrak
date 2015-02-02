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
