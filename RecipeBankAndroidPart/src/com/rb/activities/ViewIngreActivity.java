package com.rb.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rb.util.Ipconfig;

public class ViewIngreActivity extends Activity {
	private final String ipaddress=Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/getRecipeIngredient";
	private final String METHOD_NAME = "getRecipeIngredient";
	
	
	private final String URL1 = "http://"+ipaddress+"/RecipeBankWebServices/services/ShoppingList?wsdl";
	private final String SOAP_ACTION1 = "http://webServices.rb.com/addIngredientsIntoShoppingListByRecipeId";
	private final String METHOD_NAME1 = "addIngredientsIntoShoppingListByRecipeId";
	private String TAG = "Reci";
	ArrayList<String> al=new ArrayList<String>();
	int recipeId=0;
	ListView lv=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ingre);
		Intent intent=getIntent();
		recipeId=Integer.parseInt(intent.getStringExtra("recipeid"));
		lv=(ListView) findViewById(R.id.listView1);
		System.out.println("Recipe Id "+recipeId);
		System.out.println("Account Id "+LoginActivity.accountID);
		asyncCallCat task = new asyncCallCat();
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_ingre, menu);
		return true;
	}
	
	public void AddToShoppinglist(View view)
	{
		if(LoginActivity.accountID>0)
		{
		asyncCall task = new asyncCall();
		task.execute();
		}
		else
		{
			Toast.makeText(ViewIngreActivity.this,"Login to use this feature ", Toast.LENGTH_SHORT).show();
		}
	}
	
	/***************/


	private class asyncCallCat extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			getRecipeIngre();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			 ArrayAdapter<String> arrayAdapter =      
	                 new ArrayAdapter<String>(ViewIngreActivity.this,android.R.layout.simple_list_item_1, al);
	                
	                lv.setAdapter(arrayAdapter);
	                
			 
	            
	                 
			Log.i(TAG, "onPostExecute");
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

	/***************/
	public void getRecipeIngre()
	{
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("recipeId");// Define the variable name in the web
		// service method
		
		KeyProp.setValue(recipeId);// set value for userName variable
		KeyProp.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp);// Pass properties to the variable
		
	

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result " + response);
			
			JSONArray jsonArray=new JSONArray(response.toString());
			System.out.println("jsonArray.length:"+jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				
				JSONObject jObject=jsonArray.getJSONObject(i);
				//System.out.println("RecipeId="+jObject.get("RecipeId"));
//				System.out.println("photo="+jObject.get("photo"));
				
				al.add(jObject.get("IngredientName").toString()+"     "+jObject.get("IngredientQuanlity").toString()+" "+jObject.get("ingredientMeasure").toString());
				
				
				
				
			}
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
	
	/***************/


	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			AddIngredientToShoppinglist();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			Toast.makeText(ViewIngreActivity.this,
					"Ingredients added to shopping list", Toast.LENGTH_SHORT)
					.show();
	            
	                 
			Log.i(TAG, "onPostExecute");
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

	/***************/
	public void AddIngredientToShoppinglist()
	{
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("recipeId");// Define the variable name in the web
		KeyProp.setValue(recipeId);// set value for userName variable
		KeyProp.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp);// Pass properties to the variable
		
		// Pass value for accountif variable of the web service
		PropertyInfo accountIdProp = new PropertyInfo();
		accountIdProp.setName("accountId");
		accountIdProp.setValue(LoginActivity.accountID);
		accountIdProp.setType(Integer.class);
		request.addProperty(accountIdProp);
		
	

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL1);

		try {
			androidHttpTransport.call(SOAP_ACTION1, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result " + response);
			

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}


}
