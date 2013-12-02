package com.rb.activities;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rb.util.Ipconfig;

public class AddStepsActivity extends Activity {
	private String TAG = "Steps";
	public static final int RESULT_GALLERY = 0;
	String byteString = "";
	String et1val1="";
	String et1val2="";
	private final String ipaddress=Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/insertRecipeStep";
	private final String METHOD_NAME = "insertRecipeStep";
	EditText et1;
	EditText et2;

	ImageButton ib1;

	Button btnFinish;
	Button btnNext;
	String recipeId=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		recipeId=intent.getStringExtra("recipeId");
		setContentView(R.layout.activity_add_steps);
		et1=(EditText) findViewById(R.id.editText1);
		et2=(EditText) findViewById(R.id.editText2);
		
		ib1=(ImageButton) findViewById(R.id.imageButton1);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_steps, menu);
		return true;
	}
	
	public void NextStep(View view)
	{
		finish();
		startActivity(getIntent());
	}
	
	public void Finish(View view)
	{
		Toast toast = Toast.makeText(this,"Recipe Created", Toast.LENGTH_LONG);
		toast.show();
	}
	
	public void InsertStep(View view)
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
				SetRecipeSteps();
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
	
	public void SetRecipeSteps() throws JSONException
	{
		et1val1=et1.getText().toString();
		et1val2=et2.getText().toString();
	
		
		JSONObject json=new JSONObject();
		json.put("stepTime", et1val1);
		json.put("StepDesc", et1val2);
		json.put("RecipeId", recipeId);
		json.put("StepPhoto", byteString);
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("stepObject");// Define the variable name in the web
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
			Log.i(TAG, "Result " + response);
			

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
	/*************/
	public void OpenGallery(View view)
	{
		Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(galleryIntent , RESULT_GALLERY );
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	         

			if (requestCode == RESULT_GALLERY && resultCode == RESULT_OK
					&& null != data) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();

				System.out.println(picturePath);
				// String picturePath contains the path of selected Image

				//ImageView imageView = (ImageView) findViewById(R.id.imageButton1);
				ib1.setImageBitmap(BitmapFactory.decodeFile(picturePath));

				Bitmap bm = BitmapFactory.decodeFile(picturePath);
				byte[] bt = BitmapToBytes(bm);
				byteString = Base64.encode(bt);
	         
	        }
	 }
	 
	 private byte[] BitmapToBytes(Bitmap bm) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.PNG, 10, baos);
			return baos.toByteArray();
		}


}
