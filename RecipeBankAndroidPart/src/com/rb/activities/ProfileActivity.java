package com.rb.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;
import com.rb.activities.R;

public class ProfileActivity extends Activity {

	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://10.24.49.52:8088/RecipeBankWebServices1/services/Account?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/Account";
	private final String METHOD_NAME = "Account";

	Boolean result = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_page);

		Button login = (Button) findViewById(R.id.button1);
		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// loginAction();
				asyncCall task = new asyncCall();
				task.execute();
			
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed_view, menu);
		return true;
	}

	/***************/
	private String TAG = "Vik";

	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			ProfileAction();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if(ProfileActivity.this.result)
			{
			Intent intent=new Intent(ProfileActivity.this,HomPageActivity.class);
			startActivity(intent);
			}
			else
			{
				Toast.makeText(ProfileActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
			}
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
	private void ProfileAction() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

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
			result = Boolean.valueOf(response.toString());
			
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
}
