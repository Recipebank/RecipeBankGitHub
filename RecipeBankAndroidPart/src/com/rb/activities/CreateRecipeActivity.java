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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.rb.util.Ipconfig;

public class CreateRecipeActivity extends Activity {
	public static final int RESULT_GALLERY = 0;
	String byteString = null;
	private final String ipaddress=Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://"+ipaddress+"/RecipeBankWebServices/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/CreateRecipe";
	private final String METHOD_NAME = "CreateRecipe";
	private String TAG = "Create";
	EditText et1=null;
	EditText et2=null;
	ImageButton Ib=null;
	String title=null;
	String desc=null;
	String recipeId=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_recipe);
		et1=(EditText) findViewById(R.id.editText1);
		et2=(EditText) findViewById(R.id.editText2);
		Ib=(ImageButton) findViewById(R.id.imageButton1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_recipe, menu);
		return true;
	}
	
	public void NextPage(View view)
	{
		asyncCall task = new asyncCall();
		task.execute();
	}
	
	/***************/


	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			try {
				SetRecipeDetail();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Intent intent=new Intent(CreateRecipeActivity.this,CreateIngreActivity.class);
			intent.putExtra("recipeId", recipeId);
			startActivity(intent);
			
	                
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
	
	public void SetRecipeDetail() throws JSONException
	{
		title=et1.getText().toString();
		desc=et2.getText().toString();
		
		JSONObject json=new JSONObject();
		json.put("AccountId", 1);
		json.put("RecipeTitle", title);
		json.put("Description", desc);
		json.put("Photo", byteString);
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("recipeString");// Define the variable name in the web
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
			JSONObject jObject=new JSONObject(response.toString());
			recipeId=jObject.get("RecipeId").toString();

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
	
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
				Ib.setImageBitmap(BitmapFactory.decodeFile(picturePath));

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
