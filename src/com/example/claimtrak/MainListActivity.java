package com.example.claimtrak;

import java.util.ArrayList;
import java.util.Collection;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainListActivity extends ListActivity {
	//private String[] currencies = {"CAD", "EUR", "USD"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_list);
		ClaimListManager.initManager(this.getApplicationContext());
		
		// Display claim in ListView
		ListView listView = (ListView) findViewById(R.id.expenseListView);
		Collection<Claim> claims = ClaimController.getClaimList().getClaims();	
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_expandable_list_item_1, list);
		listView.setAdapter(claimAdapter);
				
		// add change observer
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
				
		// set longclick
				
				
		/* Populate spinner
		Spinner spinner = (Spinner) findViewById(R.id.currencySpinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		//to get spinner value
		//Spinner spinner = (Spinner) findViewById(R.id.currencySpinner);
		//String text = mySpinner.getSelectedItem().toString();
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_list, menu);
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
	
	public void addClaim(MenuItem menu) {
    	Toast.makeText(this, "Add Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainListActivity.this, AddClaimActivity.class);
    	startActivity(intent);
    }
}
