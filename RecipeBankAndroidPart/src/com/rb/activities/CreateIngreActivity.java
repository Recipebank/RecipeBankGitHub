package com.rb.activities;

import org.json.JSONException;
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
import android.widget.EditText;


import com.rb.util.Ipconfig;

public class CreateIngreActivity extends Activity {
	String recipeId=null;
	private final String ipaddress=Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/insertIngredient";
	private final String METHOD_NAME = "insertIngredient";
	private String TAG = "Incredi";
	EditText et1=null;
	EditText et2=null;
	String etval1=null;
	String etval2=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_ingre);
		Intent intent=getIntent();
		recipeId=intent.getStringExtra("recipeId");
		et1=(EditText) findViewById(R.id.editText1);
		et2=(EditText) findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_ingre, menu);
		return true;
	}
	
	public void AddIngre(View view)
	{
		finish();
		startActivity(getIntent());
	}
	
	public void NextPage(View view)
	{
		
		Intent intent=new Intent(CreateIngreActivity.this,AddStepsActivity.class);
		intent.putExtra("recipeId", recipeId);
		startActivity(intent);
		
		
	}
	public void InsertIngre(View view)
	{
		AsyncCall task = new AsyncCall();
		task.execute();
	}
	
	/***************/


	private class AsyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			try {
				SetIngredients();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
				
	                
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

	
	/**
	 * @throws JSONException *************/
	
	public void SetIngredients() throws JSONException
	{
		etval1=et1.getText().toString();
		etval2=et2.getText().toString();
	
		
		JSONObject json=new JSONObject();
		json.put("ingredientMeasure", etval1);
		json.put("IngredientQuanlity", etval2);
		json.put("RecipeId", recipeId);
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("ingredientObject");// Define the variable name in the web// service method	
		KeyProp.setValue(json.toString());// set value for userName variable
		KeyProp.setType(String.class);// Define the type of the variable
		request.addProperty(KeyProp);// Pass properties to the variable
		
	

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
