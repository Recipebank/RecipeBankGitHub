package com.rb.testClient;

import org.json.JSONObject;

import com.rb.testClient.LoginStub.CreateNewAccount;
import com.rb.testClient.LoginStub.CreateNewAccountResponse;
import com.rb.testClient.LoginStub.LoginAndGetUserInfoResponse;

public class LoginClient {

	// TODO Auto-generated constructor stub
	public static void main(String[] args) throws Exception {
		LoginStub stub = new LoginStub();

/*		// Creating the request
		com.rb.testClient.LoginStub.LoginAndGetUserInfo request = new com.rb.testClient.LoginStub.LoginAndGetUserInfo();
		request.setUserName("admin");
		request.setPassword("123");

		// Invoking the service
		LoginAndGetUserInfoResponse response = stub.loginAndGetUserInfo(request);
		System.out.println("Response : " + response.get_return());*/
		
		// Creating the request
		com.rb.testClient.LoginStub.CreateNewAccount request = new com.rb.testClient.LoginStub.CreateNewAccount();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("NickName", "wwww");
		jsonObject.put("GmailAddress", "aaa@gmail.com");
		jsonObject.put("Password", "232");
		request.setJsonObject(jsonObject.toString());
		CreateNewAccountResponse response = stub.createNewAccount(request);
		System.out.println("Response : " + response.get_return());
		
	}

}
