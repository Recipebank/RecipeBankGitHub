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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rb.util.Ipconfig;

public class DetailedViewActivity extends Activity {
	private final String ipaddress = Ipconfig.ipaddress;
	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://" + ipaddress
			+ "/RecipeBankWebServices/services/Recipe?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/searchRecipeById";
	private final String METHOD_NAME = "searchRecipeById";

	private final String URL1 = "http://" + ipaddress
			+ "/RecipeBankWebServices/services/Favourite?wsdl";
	private final String SOAP_ACTION1 = "http://webServices.rb.com/addFavourite";
	private final String METHOD_NAME1 = "addFavourite";

	private final String SOAP_ACTION2 = "http://webServices.rb.com/rateRecipe";
	private final String METHOD_NAME2 = "rateRecipe";

	private final String URL2 = "http://" + ipaddress
			+ "/RecipeBankWebServices/services/Comment?wsdl";
	private final String SOAP_ACTION3 = "http://webServices.rb.com/setComment";
	private final String METHOD_NAME3 = "setComment";

	private String TAG = "Reci";
	int recipeId = 0;
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<byte[]> phtArr = new ArrayList<byte[]>();
	ArrayList<String> alDesc = new ArrayList<String>();
	ArrayList<String> alowner = new ArrayList<String>();
	ArrayList<String> alrate = new ArrayList<String>();
	InputStream is = null;
	byte[] Data = new byte[1024 * 1024];
	final Context context = this;
	TextView tv1 = null;
	TextView tv2 = null;
	TextView tv3 = null;

	ImageView im1 = null;

	ProgressDialog progressDialog;

	float rate = 0;
	int setRate = 0;
	String comment = "";

	RatingBar ratingBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_view);
		Intent intent = getIntent();
		recipeId = Integer.parseInt(intent.getStringExtra("recipeId"));
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv3 = (TextView) findViewById(R.id.textView3);
		im1 = (ImageView) findViewById(R.id.imageView1);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		asyncCallCat task = new asyncCallCat();
		task.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addfavourite:
			if (LoginActivity.accountID > 0) {
			asyncCall task = new asyncCall();
			task.execute();
			}
			else {
				Toast.makeText(DetailedViewActivity.this,
						"Login to use this feature ", Toast.LENGTH_SHORT)
						.show();
			}
			break;

		case R.id.rateit:

			LayoutInflater li = LayoutInflater.from(context);
			View promptsView = li.inflate(R.layout.rateit, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);

			final NumberPicker np = (NumberPicker) promptsView
					.findViewById(R.id.numberPicker1);
			np.setMaxValue(5);
			np.setMinValue(0);

			// set dialog message
			alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									setRate = np.getValue();
									asyncCallRate task = new asyncCallRate();
									task.execute();
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

			break;
		case R.id.addcomment:
			if (LoginActivity.accountID > 0) {
				LayoutInflater li1 = LayoutInflater.from(context);
				View commentsView = li1.inflate(R.layout.comment, null);

				AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(
						context);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder1.setView(commentsView);

				final EditText cmm = (EditText) commentsView
						.findViewById(R.id.editTextcomment);

				// set dialog message
				alertDialogBuilder1
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										comment = cmm.getText().toString();
										asyncCallComment task = new asyncCallComment();
										task.execute();
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog1 = alertDialogBuilder1.create();

				// show it
				alertDialog1.show();
			} else {
				Toast.makeText(DetailedViewActivity.this,
						"Login to use this feature ", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		case R.id.viewcomment:
			Intent intent = new Intent(DetailedViewActivity.this,
					ViewCommentActivity.class);
			intent.putExtra("recipeId", recipeId);
			startActivity(intent);
			break;

		}
		return true;
	}

	/***************/

	private class asyncCallComment extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			AddComment();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(DetailedViewActivity.this, "Comment Added ",
					Toast.LENGTH_SHORT).show();
			Log.i(TAG, "onPostExecute");
		}

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			// progressDialog = ProgressDialog.show(DetailedViewActivity.this,
			// "Connecting", "Please wait");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/***************/
	public void AddComment() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME3);
		
		PropertyInfo KeyProp1 = new PropertyInfo();
		KeyProp1.setName("accountId");// Define the variable name in the web
		// service method

		KeyProp1.setValue(LoginActivity.accountID);// set value for userName
													// variable
		KeyProp1.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp1);// Pass properties to the variable
		
		
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("recipeId");// Define the variable name in the web
		// service method

		KeyProp.setValue(recipeId);// set value for userName variable
		KeyProp.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp);// Pass properties to the variable

		

		PropertyInfo KeyProp2 = new PropertyInfo();
		KeyProp2.setName("info");// Define the variable name in the web
		// service method

		KeyProp2.setValue(comment);// set value for userName variable
		KeyProp2.setType(String.class);// Define the type of the variable
		request.addProperty(KeyProp2);// Pass properties to the variable

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL2);

		try {
			androidHttpTransport.call(SOAP_ACTION3, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result " + response);

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}

	/***************/

	/***************/

	private class asyncCallRate extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			RateIt();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			Log.i(TAG, "onPostExecute");
		}

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			// progressDialog = ProgressDialog.show(DetailedViewActivity.this,
			// "Connecting", "Please wait");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/***************/
	public void RateIt() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("recipeId");// Define the variable name in the web
		// service method

		KeyProp.setValue(recipeId);// set value for userName variable
		KeyProp.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp);// Pass properties to the variable

		PropertyInfo KeyProp1 = new PropertyInfo();
		KeyProp1.setName("rate");// Define the variable name in the web
		// service method

		KeyProp1.setValue(setRate);// set value for userName variable
		KeyProp1.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp1);// Pass properties to the variable

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION2, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result " + response);

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
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
			// progressDialog.dismiss();
			tv1.setText(al.get(0));

			Data = phtArr.get(0);
			is = new ByteArrayInputStream(Data);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inPreferredConfig = Config.RGB_565;
			options.inDither = true;
			Bitmap bmp0 = BitmapFactory.decodeByteArray(Data, 0, Data.length,
					options);

			im1.setImageBitmap(bmp0);
			tv2.setText(alDesc.get(0));
			tv3.setText("Owner: " + alowner.get(0));
			rate = Float.parseFloat(alrate.get(0));
			ratingBar.setRating(rate);

			Log.i(TAG, "onPostExecute");
		}

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			// progressDialog = ProgressDialog.show(DetailedViewActivity.this,
			// "Connecting", "Please wait");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/***************/
	public void getRecipeDetail() {
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

			JSONArray jsonArray = new JSONArray(response.toString());
			System.out.println("jsonArray.length:" + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jObject = jsonArray.getJSONObject(i);
				// System.out.println("RecipeId="+jObject.get("RecipeId"));
				// System.out.println("photo="+jObject.get("photo"));

				al.add(jObject.get("RecipeTitle").toString());
				phtArr.add(Base64.decode(jObject.get("Photo").toString()));
				alDesc.add(jObject.get("Description").toString());
				alowner.add(jObject.get("NickName").toString());
				alrate.add(jObject.get("Rate").toString());

			}
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}

	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			AddToFavourite();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Toast toast = Toast.makeText(DetailedViewActivity.this,
					"Recipe Added to favourite", Toast.LENGTH_LONG);

			Log.i(TAG, "onPostExecute");
		}

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			// progressDialog = ProgressDialog.show(DetailedViewActivity.this,
			// "Connecting", "Please wait");
			Log.i(TAG, "onPreExecute");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			Log.i(TAG, "onProgressUpdate");
		}

	}

	/***************/
	public void AddToFavourite() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
		
		PropertyInfo KeyProp1 = new PropertyInfo();
		KeyProp1.setName("accountId");// Define the variable name in the web
		// service method

		KeyProp1.setValue(LoginActivity.accountID);// set value for userName
													// variable
		KeyProp1.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp1);// Pass properties to the variable
		
		PropertyInfo KeyProp = new PropertyInfo();
		KeyProp.setName("recipeId");// Define the variable name in the web
		// service method

		KeyProp.setValue(recipeId);// set value for userName variable
		KeyProp.setType(Integer.class);// Define the type of the variable
		request.addProperty(KeyProp);// Pass properties to the variable

		
		Log.e(TAG, "recipe Id" + recipeId);
		Log.e(TAG, "acount id" + LoginActivity.accountID);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL1);

		try {
			androidHttpTransport.call(SOAP_ACTION1, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result " + response);

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}

	public void ViewSteps(View view) {
		Intent intent = new Intent(DetailedViewActivity.this,
				StepsActivity.class);
		String recipeID = String.valueOf(recipeId);
		intent.putExtra("recipeid", recipeID);
		startActivity(intent);
	}

	public void ViewIngredients(View view) {
		Intent intent = new Intent(DetailedViewActivity.this,
				ViewIngreActivity.class);
		String recipeID = String.valueOf(recipeId);
		intent.putExtra("recipeid", recipeID);
		startActivity(intent);
	}

}
