package com.example.claimtrak;

import java.util.ArrayList;
import java.util.Collection;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainListActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_list);
		
		ClaimListManager.initManager(this.getApplicationContext());

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
	public void onStart() {
		super.onStart();
				
		// Display claim in ListView
		ListView listView = getListView();
		Collection<Claim> claims = ClaimController.getClaimList().getClaims();
		final ArrayList<Claim> list1 = new ArrayList<Claim>(claims);
		final ArrayList<String> list2 = new ArrayList<String>();
		for(Claim item: list1) {
			list2.add(item.getClaimName());
		}
		final ArrayAdapter<String> claimAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list2);
		listView.setAdapter(claimAdapter);
				
		// add change observer
		ClaimController.getClaimList().addListener(new Listener() {
			@Override
			public void update() {
				list2.clear();
				Collection<Claim> claims;	
				claims = ClaimController.getClaimList().getClaims();
				for(Claim item: claims) {
					list2.add(item.getClaimName());
				}
				claimAdapter.notifyDataSetChanged();
			}
						
		});
		
		// set onclick for list items
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final int finalPosition = position;
				String claimName = list2.get(finalPosition);
				Claim viewClaim = new Claim();
				Collection<Claim> claims = ClaimController.getClaimList().getClaims();
				final ArrayList<Claim> list3 = new ArrayList<Claim>(claims);
				for (Claim item: list3) {
					if (item.getClaimName().equals(claimName)) {
						viewClaim = item;
					}
				}
				
				Intent intent = new Intent(MainListActivity.this, ExpenseListActivity.class);
				GlobalClaim.claim = viewClaim;
		    	startActivity(intent);
			}
			
		});
		
		// set longclick delete/edit
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AlertDialog.Builder adb = new AlertDialog.Builder(MainListActivity.this);
				adb.setMessage("Change" + list2.get(position).toString() + "?");
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String claimName = list2.get(finalPosition);
						Claim deletedClaim = new Claim();
						Collection<Claim> claims = ClaimController.getClaimList().getClaims();
						final ArrayList<Claim> list3 = new ArrayList<Claim>(claims);
						for (Claim item: list3) {
							if (item.getClaimName().equals(claimName)) {
								deletedClaim = item;
							}
						}
						ClaimController.getClaimList().removeClaim(deletedClaim);
					}
					
				});
				
				adb.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String claimName = list2.get(finalPosition);
						Claim editClaim = new Claim();
						Collection<Claim> claims = ClaimController.getClaimList().getClaims();
						final ArrayList<Claim> list3 = new ArrayList<Claim>(claims);
						for (Claim item: list3) {
							if (item.getClaimName().equals(claimName)) {
								editClaim = item;
							}
						}
						
						Intent intent = new Intent(MainListActivity.this, EditClaim.class);
						GlobalClaim.claim = editClaim;
				    	startActivity(intent);
						
					}
				});
				
				adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				adb.show();
				return true;
			}
			
		});
				
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
