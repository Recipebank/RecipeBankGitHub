package com.example.testconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

	private final String NAMESPACE = "http://ws.userlogin.com";
	private final String URL = "http://10.24.12.63:8088/RecipeBank/services/Login?wsdl";
	private final String SOAP_ACTION = "http://ws.userlogin.com/authentication";
	private final String METHOD_NAME = "authentication";

	String result = "ok";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button login = (Button) findViewById(R.id.btn_login);
		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// loginAction();
				asyncCall task = new asyncCall();
				task.execute();
				TextView result = (TextView) findViewById(R.id.txtStatus);
				result.setText(Login.this.result);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/***************/
	private String TAG = "Vik";

	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			loginAction();
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

	/***************/
	private void loginAction() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		EditText userName = (EditText) findViewById(R.id.txtUN);
		String user_Name = userName.getText().toString();
		EditText userPassword = (EditText) findViewById(R.id.txtPW);
		String user_Password = userPassword.getText().toString();

		// Pass value for userName variable of the web service
		PropertyInfo unameProp = new PropertyInfo();
		unameProp.setName("userName");// Define the variable name in the web
										// service method
		unameProp.setValue(user_Name);// set value for userName variable
		unameProp.setType(String.class);// Define the type of the variable
		request.addProperty(unameProp);// Pass properties to the variable

		// Pass value for Password variable of the web service
		PropertyInfo passwordProp = new PropertyInfo();
		passwordProp.setName("password");
		passwordProp.setValue(user_Password);
		passwordProp.setType(String.class);
		request.addProperty(passwordProp);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result Fahrenheit: " + response);
			// TextView result = (TextView) findViewById(R.id.txtStatus);
			System.out.println("Response String is " + response.toString());
			result = response.toString();
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
}
