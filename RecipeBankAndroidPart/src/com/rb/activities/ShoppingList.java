package com.rb.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;

import com.rb.util.ExpandableListAdapter;
import com.rb.util.Ipconfig;

public class ShoppingList extends Activity {

//	int accountId = 1; //Should be update from the previous activity and it is using to view the shoppping list
	private String TAG = "ShopUI";
	ListView lv;
	ArrayList<String> IngredientsList = null;
	List<String> listDataHeader = new ArrayList<String>();
	List<String> itemIndex = null;
	HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
	HashMap<String, List<String>> childIndex = new HashMap<String, List<String>>();
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	Integer shopIngId = -1;
	int ingState = 0;
	ArrayList<Integer> buffIndex = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_list);
		// lv = (ListView) findViewById(R.id.listView1);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		asyncViewAll task = new asyncViewAll();
		task.execute();

		// Adding Listener
		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				String stateString="";
				shopIngId = Integer.parseInt(childIndex.get(
						listDataHeader.get(groupPosition)).get(childPosition));
				System.out.println(shopIngId);
				if (buffIndex.indexOf(shopIngId) != -1) {
					v.setBackgroundColor(new Color().parseColor("#f4f4f4"));
					buffIndex.remove(buffIndex.indexOf(shopIngId));
					ingState = 0;
					stateString = " has resumed";
				} else {
					buffIndex.add(shopIngId);
					v.setBackgroundColor(Color.LTGRAY);
					ingState = 1;
					stateString = " is done";
				}
				asyncChangeState changeTask = new asyncChangeState();
				changeTask.execute();
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition)+stateString, Toast.LENGTH_SHORT)
						.show();

				return false;
			}
		});
	}

	/* Listening Ending */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shopping_list, menu);
		return true;
	}

	private class asyncViewAll extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			ShoppingListControl slc = new ShoppingListControl(
					"viewShoppingList");
			slc.excuteAll();
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i(TAG, "onPostExecute");
			// ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
			// ShoppingList.this, android.R.layout.simple_list_item_1,
			// IngredientsList);
			//
			// lv.setAdapter(arrayAdapter);
			listAdapter = new ExpandableListAdapter(ShoppingList.this,
					listDataHeader, listDataChild);
			// setting list adapter
			expListView.setAdapter(listAdapter);

		}

		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	private class asyncChangeState extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			// IngredientsList = null;
			// listDataHeader = new ArrayList<String>();
			// itemIndex = null;
			// listDataChild = new HashMap<String, List<String>>();
			// childIndex = new HashMap<String, List<String>>();

			ShoppingListControl slc = new ShoppingListControl(
					"changeShoppingListState");
			slc.changeState();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i(TAG, "onPostExecute");

			// listAdapter = new ExpandableListAdapter(ShoppingList.this,
			// listDataHeader, listDataChild);
			// // setting list adapter
			// expListView.setAdapter(listAdapter);

		}

		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	private class ShoppingListControl {
		private final String ipaddress=Ipconfig.ipaddress;
		private final String NAMESPACE = "http://webServices.rb.com";
		private final String URL = "http://" + ipaddress+ "/RecipeBankWebServices/services/ShoppingList?wsdl";
		private String SOAP_ACTION = "";

		private String METHOD_NAME = "";
		private String TAG = "shop";

		public ShoppingListControl(String method) {

			METHOD_NAME = method;
			SOAP_ACTION = "http://webServices.rb.com/" + method;
			// METHOD_NAME = method;
		}

		public void excuteAll() {
			System.out.println("---------------" + URL);
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			PropertyInfo shopListProp = new PropertyInfo();
			shopListProp.setName("accountId");// Define the variable name in the
												// web
			// service method

			shopListProp.setValue(LoginActivity.accountID);// set value for userName variable
			//System.out.println("accountID " + LoginActivity.accountID);
			shopListProp.setType(Integer.class);// Define the type of the
												// variable
			request.addProperty(shopListProp);// Pass properties to the variable

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

			try {
				androidHttpTransport.call(SOAP_ACTION, envelope);
				SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
				Log.i(TAG, "Result " + response);

				JSONArray jsonArray = new JSONArray(response.toString());

				System.out.println("jsonArray.length:" + jsonArray.length());

				int cou = -1;
				String rTitle = "";
				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject jObject = jsonArray.getJSONObject(i);
					int id = Integer.parseInt(jObject.get("RecipeId")
							.toString());
					// System.out.println(jObject.toString());
					if (cou == id) {
						IngredientsList.add(jObject.get("IngredientName")
								.toString()
								+ "    "
								+ jObject.get("IngredientQuanlity").toString()
								+ " "
								+ jObject.get("IngredientMeasure").toString());
						itemIndex.add(jObject.get("ShoppingIngredientsId")
								.toString());
					} else {
						listDataHeader.add(jObject.get("RecipeTitle")
								.toString());
						if (IngredientsList == null) {
							IngredientsList = new ArrayList<String>();
							itemIndex = new ArrayList<String>();
							rTitle = jObject.get("RecipeTitle").toString();
							IngredientsList.add(jObject.get("IngredientName")
									.toString()
									+ "    "
									+ jObject.get("IngredientQuanlity")
											.toString()
									+ " "
									+ jObject.get("IngredientMeasure")
											.toString());
							itemIndex.add(jObject.get("ShoppingIngredientsId")
									.toString());
							cou = id;
						} else {
							listDataChild.put(rTitle, IngredientsList);
							childIndex.put(rTitle, itemIndex);
							IngredientsList = new ArrayList<String>();
							itemIndex = new ArrayList<String>();
							rTitle = jObject.get("RecipeTitle").toString();
							IngredientsList.add(jObject.get("IngredientName")
									.toString()
									+ "    "
									+ jObject.get("IngredientQuanlity")
											.toString()
									+ " "
									+ jObject.get("IngredientMeasure")
											.toString());
							itemIndex.add(jObject.get("ShoppingIngredientsId")
									.toString());
							cou = id;
						}
					}
				}
				listDataChild.put(rTitle, IngredientsList);
				childIndex.put(rTitle, itemIndex);

			} catch (Exception e) {
				Log.e(TAG, "Error: " + e.getMessage());
			}
		}

		public void changeState() {

			System.out.println(SOAP_ACTION);
			System.out.println(shopIngId);
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			PropertyInfo shopIngIdProp = new PropertyInfo();
			shopIngIdProp.setName("shoppingIngredientsId");
			shopIngIdProp.setValue(shopIngId);// set value for userName variable
			shopIngIdProp.setType(Integer.class);// Define the type of the
			request.addProperty(shopIngIdProp);

			PropertyInfo shopIngStateProp = new PropertyInfo();// variable
			shopIngStateProp.setName("state");
			shopIngStateProp.setValue(ingState);
			shopIngStateProp.setType(Integer.class);
			request.addProperty(shopIngStateProp);// Pass properties to the
													// variable

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

			try {
				androidHttpTransport.call(SOAP_ACTION, envelope);
				SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
				Log.i(TAG, "Result " + response);

			} catch (Exception e) {
				Log.e(TAG, "Error: " + e.getMessage());
			}
		}

	}

}
