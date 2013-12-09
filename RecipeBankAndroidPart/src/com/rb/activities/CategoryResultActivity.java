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
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rb.util.Ipconfig;

public class CategoryResultActivity extends Activity {
		private final String ipaddress=Ipconfig.ipaddress;
		private final String NAMESPACE = "http://webServices.rb.com";
		private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Recipe?wsdl";
		private final String SOAP_ACTION = "http://webServices.rb.com/searchRecipeByCategory";
		private final String METHOD_NAME = "searchRecipeByCategory";
		private String TAG = "Reci";
		private int key=0;
		ArrayList<String> searchList=new ArrayList<String>();
		ArrayList<String> RecipeIdList=new ArrayList<String>();
		ListView lv=null;
		ProgressDialog progressDialog;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_search_result);
			Intent intent=getIntent();
			key=Integer.parseInt(intent.getStringExtra("categoryId"));
			lv=(ListView) findViewById(R.id.listView1);
			asyncCallCat task = new asyncCallCat();
			task.execute();
			
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int pos,long id) {
					// TODO Auto-generated method stub
					String recipeId=RecipeIdList.get(pos);
					Intent intent=new Intent(CategoryResultActivity.this,DetailedViewActivity.class);
					intent.putExtra("recipeId",recipeId );
					startActivity(intent);
				}
			} );
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.search_result, menu);
			return true;
		}
		
		/***************/


		private class asyncCallCat extends AsyncTask<Void, Void, Void> {

			@Override
			protected Void doInBackground(Void... params) {
				Log.i(TAG, "doInBackground");
				getSearchResult();
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
			//	progressDialog.dismiss();
				 ArrayAdapter<String> arrayAdapter =      
		                 new ArrayAdapter<String>(CategoryResultActivity.this,android.R.layout.simple_list_item_1, searchList);
		                
		                lv.setAdapter(arrayAdapter);
		                
				 
		            
		                 
				Log.i(TAG, "onPostExecute");
			}

			@Override
			protected void onPreExecute() {
			//	super.onPreExecute();
			//	progressDialog = ProgressDialog.show(SearchResultActivity.this, "Processing", "Please wait");
				Log.i(TAG, "onPreExecute");
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				Log.i(TAG, "onProgressUpdate");
			}

		}

		/***************/
		public void getSearchResult()
		{
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			PropertyInfo KeyProp = new PropertyInfo();
			KeyProp.setName("categoryId");// Define the variable name in the web
			// service method
			
			KeyProp.setValue(key);// set value for userName variable
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
//					System.out.println("photo="+jObject.get("photo"));
					
					searchList.add(jObject.get("RecipeTitle").toString());
					RecipeIdList.add(jObject.get("RecipeId").toString());
					
					
					
				}
				// result.setText(response.toString());
				// result.setText("OK");

			} catch (Exception e) {
				Log.e(TAG, "Error: " + e.getMessage());
			}
		}
}
