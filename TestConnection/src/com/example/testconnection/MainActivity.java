package com.example.testconnection;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private static int RESULT_LOAD_IMAGE = 1;
	String byteString = null;

	private final String NAMESPACE = "http://webServices.rb.com";
	private final String URL = "http://10.24.21.60:8088/RecipeBankWebServices/services/ImageOperation?wsdl";
	private final String SOAP_ACTION = "http://webServices.rb.com/insertImage";
	private final String METHOD_NAME = "insertImage";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button mbutton = (Button) findViewById(R.id.Login);
		mbutton.setOnClickListener(MainActivity.this);
		Button buttonLoadImage = (Button) findViewById(R.id.LoadPIC);
		buttonLoadImage.setOnClickListener(MainActivity.this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Login:
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Login.class);
			startActivity(intent);
			break;
		case R.id.LoadPIC:
			Intent intent1 = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent1, RESULT_LOAD_IMAGE);

			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
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

			ImageView imageView = (ImageView) findViewById(R.id.imgView);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

			Bitmap bm = BitmapFactory.decodeFile(picturePath);
			byte[] bt = BitmapToBytes(bm);
			byteString = Base64.encode(bt);
			// try {
			// byteString = new String(bt,"UTF-8");
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
			// System.out.println("This is the value: "+byteString);

			asyncCall task = new asyncCall();
			task.execute();
			// String imager_b = Base64.encode(bt);
			// TextView tView = (TextView) findViewById(R.id.textView1);
			// tView.setText(bt.toString());
			// System.out.println(bt);
			// Bitmap rebm = BytesToBitmap(bt);
			// imageView.setImageBitmap(null);
			// imageView.setImageBitmap(rebm);

		}
	}

	private String TAG = "Vik";

	private class asyncCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.i(TAG, "doInBackground");
			sendImg();
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

	private void sendImg() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		PropertyInfo imgProp = new PropertyInfo();
		imgProp.setName("bString");

		imgProp.setValue(byteString);
		imgProp.setType(String.class);
		request.addProperty(imgProp);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			Log.i(TAG, "Result Fahrenheit: " + response);
			System.out.println("Response String is " + response.toString());

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}

	private byte[] BitmapToBytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 10, baos);
		return baos.toByteArray();
	}

	private Bitmap BytesToBitmap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

}
