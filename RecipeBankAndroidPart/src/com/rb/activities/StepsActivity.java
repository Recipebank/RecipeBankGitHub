package com.rb.activities;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StepsActivity extends Activity {
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://10.24.0.191:8088/RecipeBankWebServices1/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/getRecipeDetails";
	private final String METHOD_NAME = "getRecipeDetails";
	private String TAG = "Reci";
	ArrayList<String> al=new ArrayList<String>();
	int recipeId=0;
	ListView lv=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_steps);
		Intent intent=getIntent();
		recipeId=Integer.parseInt(intent.getStringExtra("recipeid"));
		lv=(ListView) findViewById(R.id.listView1);
		asyncCallCat task = new asyncCallCat();
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.steps, menu);
		return true;
	}
	
	/***************/


	private class asyncCallCat extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			getRecipeSteps();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			 ArrayAdapter<String> arrayAdapter =      
	                 new ArrayAdapter<String>(StepsActivity.this,android.R.layout.simple_list_item_1, al);
	                
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
	public void getRecipeSteps()
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
				
				al.add(jObject.get("StepDesc").toString());
				
				
				
				
			}
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}

}
