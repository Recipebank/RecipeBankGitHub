package com.rb.activities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

import com.rb.util.Ipconfig;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedViewActivity extends Activity {
	private final String ipaddress=Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/searchRecipeById";
	private final String METHOD_NAME = "searchRecipeById";
	private String TAG = "Reci";
	int recipeId=0;
	ArrayList<String> al=new ArrayList<String>();
	ArrayList<byte[]> phtArr=new ArrayList<byte[]>();
	ArrayList<String> alDesc=new ArrayList<String>();
	InputStream is = null;
	byte[] Data = new byte[4096];
	
	TextView tv1=null;
	TextView tv2=null;
	
	ImageView im1=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_view);
		Intent intent=getIntent();
		recipeId=Integer.parseInt(intent.getStringExtra("recipeId"));
		tv1=(TextView) findViewById(R.id.textView1);
 		tv2=(TextView) findViewById(R.id.textView2);
 		im1=(ImageView) findViewById(R.id.imageView1);
		asyncCallCat task = new asyncCallCat();
		task.execute();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed_view, menu);
		return true;
	}
	
	/***************/


	private class asyncCallCat extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			getRecipeDetail();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			tv1.setText(al.get(0));
 			
 			Data=phtArr.get(0);
 			is = new ByteArrayInputStream(Data);
 			Bitmap bmp0 = BitmapFactory.decodeByteArray(Data, 0, Data.length);
 			
 			im1.setImageBitmap(bmp0);
 			tv2.setText(alDesc.get(0));
			 
	            
	                 
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
	public void getRecipeDetail()
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
				
				al.add(jObject.get("RecipeTitle").toString());
				phtArr.add(Base64.decode(jObject.get("Photo").toString()));
				alDesc.add(jObject.get("Description").toString());
				
				
				
			}
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
	
	public void ViewSteps(View view)
	{
		Intent intent =new Intent(DetailedViewActivity.this,StepsActivity.class);
		String recipeID=String.valueOf(recipeId);
		intent.putExtra("recipeid",recipeID);
		startActivity(intent);
	}


}
