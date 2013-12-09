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
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.rb.util.Ipconfig;

public class RegisterActivity extends Activity {
	private final String ipaddress=Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Login?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/createNewAccount";
	private final String METHOD_NAME = "createNewAccount";
	private String result=null;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void Register(View view)
	{
		asyncCall task = new asyncCall();
		task.execute();
	}
	
	/***************/
	private String TAG = "Vik";

	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			try {
				RegisterAction();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			progressDialog.dismiss();
			Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
			startActivity(intent);
		
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(RegisterActivity.this, "Connecting", "Please wait");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/**
	 * @throws JSONException *************/
	private void RegisterAction() throws JSONException {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		EditText userName = (EditText) findViewById(R.id.editText1);
		String user_Name = userName.getText().toString();
		EditText email = (EditText) findViewById(R.id.editText2);
		String Email = email.getText().toString();
		EditText userPassword = (EditText) findViewById(R.id.editText3);
		String user_Password = userPassword.getText().toString();

		
		JSONObject json=new JSONObject();
		json.put("NickName", user_Name);
		json.put("GmailAddress", Email);
		json.put("Password", user_Password);
		
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("jsonObject");// Define the variable name in the web
		// service method
		
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
			Log.i(TAG, "Result Fahrenheit: " + response);
	
			System.out.println("Response String is " + response.toString());
			result = response.toString();
			

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}

}
