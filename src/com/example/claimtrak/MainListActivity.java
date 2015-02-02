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
		// init manager
		ClaimListManager.initManager(this.getApplicationContext());
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
		ClaimListManager.initManager(this.getApplicationContext());
		// Display claim in ListView
		ListView listView = getListView();
		Collection<Claim> claims = ClaimController.getClaimList().getClaims();
		final ArrayList<Claim> list1 = new ArrayList<Claim>(claims);
		final ArrayList<String> list2 = new ArrayList<String>();
		for(Claim item: list1) {
			list2.add("" + item.getClaimName() + " - (" + item.getStatus() + ")");
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
					list2.add("" + item.getClaimName() + " - (" + item.getStatus() + ")");
				}
				claimAdapter.notifyDataSetChanged();
			}
						
		});
		
		// set onclick for list items
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// get item at click position
				final int finalPosition = position;
				String claimName = list2.get(finalPosition);
				Claim viewClaim = new Claim();
				Collection<Claim> claims = ClaimController.getClaimList().getClaims();
				final ArrayList<Claim> list3 = new ArrayList<Claim>(claims);
				for (Claim item: list3) {
					if (("" + item.getClaimName() + " - (" + item.getStatus() + ")").equals(claimName)) {
						viewClaim = item;
					}
				}
				// start expense list activity
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
				// build dialog pop-up
				AlertDialog.Builder adb = new AlertDialog.Builder(MainListActivity.this);
				adb.setMessage("Change" + list2.get(position).toString() + "?");
				adb.setCancelable(true);
				final int finalPosition = position;
				// delete button
				adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// delete item at click location
						String claimName = list2.get(finalPosition);
						Claim deletedClaim = new Claim();
						Collection<Claim> claims = ClaimController.getClaimList().getClaims();
						final ArrayList<Claim> list3 = new ArrayList<Claim>(claims);
						for (Claim item: list3) {
							if (("" + item.getClaimName() + " - (" + item.getStatus() + ")").equals(claimName)) {
								deletedClaim = item;
							}
						}
						ClaimController.getClaimList().removeClaim(deletedClaim);
						ClaimController.saveClaimList();
					}
					
				});
				
				// set edit button
				adb.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String claimName = list2.get(finalPosition);
						Claim editClaim = new Claim();
						Collection<Claim> claims = ClaimController.getClaimList().getClaims();
						final ArrayList<Claim> list3 = new ArrayList<Claim>(claims);
						for (Claim item: list3) {
							if (("" + item.getClaimName() + " - (" + item.getStatus() + ")").equals(claimName)) {
								editClaim = item;
							}
						}
						//send item clicked to edit activity
						if (editClaim.getStatus().equals("Submitted") || editClaim.getStatus().equals("Approved")) {
							
						} else {
							Intent intent = new Intent(MainListActivity.this, EditClaim.class);
							GlobalClaim.claim = editClaim;
					    	startActivity(intent);
						}
						
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
	// method for adding a new claim to the claimList
	public void addClaim(MenuItem menu) {
    	Toast.makeText(this, "Add Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainListActivity.this, AddClaimActivity.class);
    	startActivity(intent);
    }
}
