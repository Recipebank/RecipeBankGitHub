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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rb.util.Ipconfig;

public class HomPageActivity extends Activity {
	private final String ipaddress=Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/getRecipesAsYouWant";
	private final String METHOD_NAME = "getRecipesAsYouWant";
	
	private final String URLCat = "http://"+ipaddress+"/RecipeBankWebServices/services/Category?wsdl";
	private final String SOAP_ACTIONCat = "http://webServices.rb.com/getAllCategories";
	private final String METHOD_NAMECat = "getAllCategories";
	private String TAG = "Reci";
	private String result=null;
	ArrayList<String> al=new ArrayList<String>();
	ArrayList<byte[]> phtArr=new ArrayList<byte[]>();
	ArrayList<String> alCat=new ArrayList<String>();
	ArrayList<String> alCatID=new ArrayList<String>();
	ArrayList<String> RecipeIdList=new ArrayList<String>();
	String recipeId=null;
	
	ListView lv=null;
	Spinner spinnerCat=null;
	InputStream is = null;
	byte[] Data = new byte[4096];
	//byte[] Data1 = new byte[4096];
	TextView tv1=null;
	TextView tv2=null;
	TextView tv3=null;
	TextView tv4=null;
	TextView tv5=null;
	
	ImageView im1=null;
	ImageView im2=null;
	ImageView im3=null;
	ImageView im4=null;
	
	Button search=null;
	
	EditText et=null;
	
	LinearLayout ll=null;
	
	public static int flag=0;
	
	ProgressDialog progressDialog;
	
	String username="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_se);
		asyncCall task = new asyncCall();
		task.execute();
	
		spinnerCat=(Spinner) findViewById(R.id.spinner1);
		asyncCallCat task1 = new asyncCallCat();
		task1.execute();
 		 
 		tv1=(TextView) findViewById(R.id.textView1);
 		tv2=(TextView) findViewById(R.id.textView2);
 		tv3=(TextView) findViewById(R.id.textView3);
 		tv4=(TextView) findViewById(R.id.textView4);
 		tv5=(TextView) findViewById(R.id.textView5);
 		
 		im1=(ImageView) findViewById(R.id.imageView1);
 		im2=(ImageView) findViewById(R.id.imageView2);
 		im3=(ImageView) findViewById(R.id.imageView3);
 		im4=(ImageView) findViewById(R.id.imageView4);
 		
 		search=(Button) findViewById(R.id.button1);
 		
 		et=(EditText) findViewById(R.id.editText1);
 	//	alCat.add("Category");
 		
		spinnerCat.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				if(pos==0)
				{
					
				}
				else{
				String categoryId=alCatID.get(pos);
				Intent intent=new Intent(HomPageActivity.this,CategoryResultActivity.class);
				intent.putExtra("categoryId", categoryId);
				startActivity(intent);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
 	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if(flag==0)
		{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		}
		else
		{
			getMenuInflater().inflate(R.menu.hom_page, menu);
			return true;
		
		}
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {    
    	switch (item.getItemId()) 
    	{        
    		case R.id.login:    
    			
    			Intent intent = new Intent(this, LoginActivity.class);
    			startActivity(intent);
    			
	    		break;        
    		
    		case R.id.register:
    			
    			Intent intent2 = new Intent(this, RegisterActivity.class);
    			startActivity(intent2);
    			
    			break;   
    			
    		case R.id.logout:
    			flag=0;
//    			 SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
//    	            if(settings.contains("username")) {
//    	                SharedPreferences.Editor editor = settings.edit();
//    	                editor.remove("username");
//    	                editor.commit();
//    	            }
    			Intent intent3 = new Intent(this, HomPageActivity.class);
    			startActivity(intent3);
    			
    			break;  
    		case R.id.add:
		
    			Intent intent4 = new Intent(this, CreateRecipeActivity.class);
    			startActivity(intent4);
		
    			break; 
    			
    		case R.id.shopping:
    			
    			Intent intent5 = new Intent(this, ShoppingList.class);
    			startActivity(intent5);
		
    			break;  
    		
    	
			
    			
    	}    
    	return true;
    }
	
	public void Search(View view)
	{
		
		Intent intent=new Intent(HomPageActivity.this,SearchResultActivity.class);
		intent.putExtra("searchKey",et.getText().toString());
		startActivity(intent);
		
	}
	
	public void DetailedView(View view)
	{
		if(view.equals(im1))
		{

			Intent intent=new Intent(HomPageActivity.this,DetailedViewActivity.class);
			intent.putExtra("recipeId",RecipeIdList.get(0));
			startActivity(intent);
		}
		else if(view.equals(im2))
		{

			Intent intent=new Intent(HomPageActivity.this,DetailedViewActivity.class);
			intent.putExtra("recipeId",RecipeIdList.get(1));
			startActivity(intent);
		}
		else if(view.equals(im3))
		{

			Intent intent=new Intent(HomPageActivity.this,DetailedViewActivity.class);
			intent.putExtra("recipeId",RecipeIdList.get(2));
			startActivity(intent);
		}
		else if(view.equals(im4))
		{

			Intent intent=new Intent(HomPageActivity.this,DetailedViewActivity.class);
			intent.putExtra("recipeId",RecipeIdList.get(3));
			startActivity(intent);
		}
	}
	
	/***************/


	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			GetAllRecipe();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			// progressDialog.dismiss();
	                  Intent intent=getIntent();
	                  try{
	                  username=intent.getStringExtra("username");
	                  if(username!=null){
	                  tv5.setText("Hi,"+username);
	                  }
	                  }catch(Exception ex){}
	                
//	                 Bitmap mutableBitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
//	                 Canvas canvas = new Canvas(mutableBitmap);
			 			//TextView tv0=new TextView(HomPageActivity.this);
			 			tv1.setText(al.get(0));
			 			//ll.addView(tv0);
			 			Data=phtArr.get(0);
			 			is = new ByteArrayInputStream(Data);
			 			BitmapFactory.Options options=new BitmapFactory.Options();
			            options.inJustDecodeBounds = false;
			            options.inPreferredConfig = Config.RGB_565;
			            options.inDither = true;
			            Bitmap bmp0 = BitmapFactory.decodeByteArray(Data, 0, Data.length,options);
			 			//ImageView imageView0 = new ImageView(HomPageActivity.this);
			 			im1.setImageBitmap(bmp0);
			 		//	TableLayout.LayoutParams parms0 = new TableLayout.LayoutParams(300,100);
			 		//	im1.setLayoutParams(parms0);
			 			//ll.addView(imageView0);
              
			 
			 
	               // TextView tv=new TextView(HomPageActivity.this);
	                tv2.setText(al.get(1));
	                 //ll.addView(tv);
	                 Data=phtArr.get(1);
	                 is = new ByteArrayInputStream(Data);
	                 BitmapFactory.Options options1=new BitmapFactory.Options();
	                 options1.inJustDecodeBounds = false;
	                 options1.inPreferredConfig = Config.RGB_565;
	                 options1.inDither = true;
	                 Bitmap bmp = BitmapFactory.decodeByteArray(Data, 0, Data.length,options1);
                	// ImageView imageView = new ImageView(HomPageActivity.this);
	                 im2.setImageBitmap(bmp);
	              //   TableLayout.LayoutParams parms = new TableLayout.LayoutParams(300,100);
	              //   im2.setLayoutParams(parms);
	                 //ll.addView(imageView);
	                 
	                 
	               //  TextView tv1=new TextView(HomPageActivity.this);
		             tv3.setText(al.get(2));
		             //ll.addView(tv1);
	                 Data=phtArr.get(2);
	                 is = new ByteArrayInputStream(Data);
	                 
	                 BitmapFactory.Options options2=new BitmapFactory.Options();
	                 options2.inJustDecodeBounds = false;
	                 options2.inPreferredConfig = Config.RGB_565;
	                 options2.inDither = true;
	                 Bitmap bmp1 = BitmapFactory.decodeByteArray(Data, 0, Data.length,options2);
                	 //ImageView imageView1 = new ImageView(HomPageActivity.this);
	                 im3.setImageBitmap(bmp1);
	              //   TableLayout.LayoutParams parms1 = new TableLayout.LayoutParams(300,100);
	              //   im3.setLayoutParams(parms1);
	                 //ll.addView(imageView1);
	                 
	                 
	                 //  TextView tv1=new TextView(HomPageActivity.this);
		             tv4.setText(al.get(3));
		             //ll.addView(tv1);
	                 Data=phtArr.get(3);
	                 is = new ByteArrayInputStream(Data);
	                 
	                 BitmapFactory.Options options3=new BitmapFactory.Options();
	                 options3.inJustDecodeBounds = false;
	                 options3.inPreferredConfig = Config.RGB_565;
	                 options3.inDither = true;
	                 Bitmap bmp2 = BitmapFactory.decodeByteArray(Data, 0, Data.length,options3);
                	 //ImageView imageView1 = new ImageView(HomPageActivity.this);
	                 im4.setImageBitmap(bmp2);
	              //   TableLayout.LayoutParams parms2 = new TableLayout.LayoutParams(300,100);
	             //    im4.setLayoutParams(parms2);
	                 //ll.addView(imageView1);
	                 
	                 
	                
	            /*     for(byte[] data : phtArr)
	                 {
	                	 is = new ByteArrayInputStream(data);
	                	 bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
	                	 imageView = new ImageView(HomPageActivity.this);
		                 imageView.setImageBitmap(bmp);
		                 ll.addView(imageView);
	                	 
	                 }
	                 */
	                
			Log.i(TAG, "onPostExecute");
		}

		@Override
		protected void onPreExecute() {
		//	super.onPreExecute();
		//	progressDialog = ProgressDialog.show(HomPageActivity.this, "Wait", "Downloading...");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/***************/
	/***************/


	private class asyncCallCat extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			GetCategoryList();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			 ArrayAdapter<String> arrayAdapter =      
	                 new ArrayAdapter<String>(HomPageActivity.this,android.R.layout.simple_list_item_1, alCat);
	                
	                spinnerCat.setAdapter(arrayAdapter);
			 
	            
	                 
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
	
	private void GetAllRecipe() {
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		PropertyInfo amountProp = new PropertyInfo();
		amountProp.setName("amount");// Define the variable name in the web
		// service method
		int num = 4;
		amountProp.setValue(num);// set value for userName variable
		amountProp.setType(Integer.class);// Define the type of the variable
		request.addProperty(amountProp);// Pass properties to the variable
		
	

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
				phtArr.add(Base64.decode(jObject.get("photo").toString()));
				RecipeIdList.add(jObject.get("RecipeId").toString());
				
				
			}
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
	
private void GetCategoryList() {
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAMECat);
		

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URLCat);

		try {
			androidHttpTransport.call(SOAP_ACTIONCat, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result " + response);
			
			JSONArray jsonArray=new JSONArray(response.toString());
			System.out.println("jsonArray.length:"+jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				
				JSONObject jObject=jsonArray.getJSONObject(i);
			//	System.out.println("RecipeId="+jObject.get("RecipeId"));
//				System.out.println("photo="+jObject.get("photo"));
				alCat.add(jObject.get("CategoryTitle").toString());
				alCatID.add(jObject.get("CategoryId").toString());
				
				
			}
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}


}
