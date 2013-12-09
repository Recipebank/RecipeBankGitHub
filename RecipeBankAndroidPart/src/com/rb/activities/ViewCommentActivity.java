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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rb.util.Ipconfig;

public class ViewCommentActivity extends Activity {
	private final String ipaddress = Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://" + ipaddress
			+ "/RecipeBankWebServices/services/Comment?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/getCommentbyRecipe";
	private final String METHOD_NAME = "getCommentbyRecipe";
	private String TAG = "Comment";
	private int recipeId = 0;
	ArrayList<String> CommentList = new ArrayList<String>();
	ListView lv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_comment);
		Intent intent = getIntent();
		recipeId = intent.getIntExtra("recipeId", 0);
		lv = (ListView) findViewById(R.id.listView1);
		asyncCallCat task = new asyncCallCat();
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_comment, menu);
		return true;
	}

	/***************/

	private class asyncCallCat extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			getComment();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// progressDialog.dismiss();
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
					ViewCommentActivity.this,
					android.R.layout.simple_list_item_1, CommentList);

			lv.setAdapter(arrayAdapter);

			Log.i(TAG, "onPostExecute");
		}

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			// progressDialog = ProgressDialog.show(SearchResultActivity.this,
			// "Processing", "Please wait");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/***************/
	public void getComment() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("recipeId");// Define the variable name in the web
		// service method
		Log.e(TAG, "RecipeID: " + recipeId);
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

			JSONArray jsonArray = new JSONArray(response.toString());
			System.out.println("jsonArray.length:" + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jObject = jsonArray.getJSONObject(i);
				// System.out.println("RecipeId="+jObject.get("RecipeId"));
				// System.out.println("photo="+jObject.get("photo"));

				CommentList.add(jObject.get("DetailInfo").toString());
				
			}
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}

}
