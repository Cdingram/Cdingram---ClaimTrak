package com.example.claimtrak;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		
		ClaimListManager.initManager(this.getApplicationContext());
		
		// Display claim in ListView
		ListView listView = (ListView) findViewById(R.id.expenseListView);
		Collection<Claim> claims = ClaimController.getClaimList().getClaims();	
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_expandable_list_item_1, list);
		listView.setAdapter(claimAdapter);
		
		// add change observer
		/*
			ClaimController.getClaimList().addListener(new Listener() {
				@Override
				public void update() {
					list.clear();
					Collection<Claim> claims;	
					claims = ClaimController.getClaimList().getClaims();
					list.addAll(claims);
					claimAdapter.notifyDataSetChanged();
				}
				
			});
		*/
		// set longclick
		
		
		
		//
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
	
	public void addClaimAction(View v) {	
		Toast.makeText(this, "Adding A Claim", Toast.LENGTH_SHORT).show();
		EditText claimCat = (EditText) findViewById(R.id.claimCategoryEditText);
		EditText claimTo = (EditText) findViewById(R.id.claimToEditText);
		EditText claimFrom = (EditText) findViewById(R.id.claimFromEditText);
		EditText claimStat = (EditText) findViewById(R.id.claimStatEditText);
		ClaimController cc = new ClaimController();
		// get all input
		//st.addStudent(new Student(textView.getText().toString()));
		String cat = claimCat.getText().toString();
		String to = claimTo.getText().toString();
		String from = claimFrom.getText().toString();
		String stat = claimStat.getText().toString();
		if (cat.length() == 0 || to.length() == 0 || from.length() == 0 || stat.length() == 0) {
			Toast.makeText(this, "Ensure all fields are filled", Toast.LENGTH_SHORT).show();
			return;
		}
		if (to.length() != 8 || from.length() != 8) {
			Toast.makeText(this, "Invalid date. Endure date is in proper format DD/MM/YY", Toast.LENGTH_SHORT).show();
			return;
		}
		
		cc.addClaim(new Claim(cat, to, from, stat));
		
	}
}
