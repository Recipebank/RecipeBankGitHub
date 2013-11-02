package com.example.testconnection;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
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
public class LoginHandler extends Activity {
	
	private final String NAMESPACE = "http://ws.userlogin.com";
	private final String URL = "http://10.24.12.151:8088/RecipeBank/services/Login?wsdl";
	private final String SOAP_ACTION = "http://ws.userlogin.com/authentication";
	private final String METHOD_NAME = "authentication";

	String result = "ok";
	private String TAG = "Vik";
	
	//����Handler����  
    Handler handler = new Handler(){
    	
    	public void handleMessage(Message msg) {
    		             
    		             super.handleMessage(msg);
    		             Bundle b = msg.getData();
    		             String res = b.getString("result");
    		             System.out.println(res);}
    	
    	
    };  
    //�½�һ���̶߳���  
    Runnable updateThread = new Runnable(){  
        //��Ҫִ�еĲ���д���̶߳����run��������  
        public void run(){  
            System.out.println("LoginThread");
            loginAction();
            //����Handler��postDelayed()����  
            //��������������ǣ���Ҫִ�е��̶߳�����뵽���е��У���ʱ������������ƶ����̶߳���  
            //��һ��������Runnable���ͣ���Ҫִ�е��̶߳���  
            //�ڶ���������long���ͣ��ӳٵ�ʱ�䣬�Ժ���Ϊ��λ  
            Message msg = new Message();
            Bundle b = new Bundle();// �������
            b.putString("result", "success");
            msg.setData(b);
            LoginHandler.this.handler.sendMessage(msg);
        }  
    };
    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_handler);
		
		Button login = (Button) findViewById(R.id.btn_login);
		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// loginAction();
//				asyncCall task = new asyncCall();
//				task.execute();
				handler.post(updateThread);
				TextView result = (TextView) findViewById(R.id.txtStatus);
				result.setText(LoginHandler.this.result);
				handler.removeCallbacks(updateThread); 
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_handler, menu);
		return true;
	}

	private void loginAction() {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		EditText userName = (EditText) findViewById(R.id.txtUN);
		String user_Name = userName.getText().toString();
		EditText userPassword = (EditText) findViewById(R.id.txtPW);
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
			Log.i(TAG, "Result Fahrenheit: " + response);
			// TextView result = (TextView) findViewById(R.id.txtStatus);
			System.out.println("Response String is " + response.toString());
			result = response.toString();
			// result.setText(response.toString());
			// result.setText("OK");

		} catch (Exception e) {
			Log.e(TAG, "Error: " + e.getMessage());
		}
	}
}
