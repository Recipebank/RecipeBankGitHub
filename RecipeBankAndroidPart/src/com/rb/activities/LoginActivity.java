package com.rb.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kobjects.base64.Base64;
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
import com.rb.util.Ipconfig;

public class LoginActivity extends Activity {
	private final String ipaddress = Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://" + ipaddress
			+ "/RecipeBankWebServices/services/Login?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/loginAndGetUserInfo";
	private final String METHOD_NAME = "loginAndGetUserInfo";
	public static int accountID=0;
	String username = "";
	String accountId = "";
	Boolean result = false;

	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

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
			progressDialog.dismiss();
			if (username!="") {
				Intent intent = new Intent(LoginActivity.this,
						HomPageActivity.class);
				HomPageActivity.flag = 1;
				intent.putExtra("username", username);
				startActivity(intent);
			} else {
				Toast.makeText(LoginActivity.this,
						"Invalid username or password", Toast.LENGTH_LONG)
						.show();
			}
			Log.i(TAG, "onPostExecute");
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(LoginActivity.this,
					"Processing", "Please wait");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/***************/

	private void savePreferences(String key, String value) {

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		Editor editor = sharedPreferences.edit();

		editor.putString(key, value);

		editor.commit();

	}

	private void loginAction() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		EditText userName = (EditText) findViewById(R.id.editText1);
		String user_Name = userName.getText().toString();
		EditText userPassword = (EditText) findViewById(R.id.editText2);
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
			Log.i(TAG, "Result " + response);

			JSONArray jsonArray = new JSONArray(response.toString());
			System.out.println("jsonArray.length:" + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jObject = jsonArray.getJSONObject(i);
	
				username = jObject.get("NickName").toString();
				accountId = jObject.get("AccountId").toString();
				accountID=Integer.parseInt(jObject.get("AccountId").toString());
				System.out.println("accountID" + accountID);
				savePreferences("accountId", accountId);

			}
		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
	
	public void Register(View view)
	{
		Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
		startActivity(intent);
	}
}
