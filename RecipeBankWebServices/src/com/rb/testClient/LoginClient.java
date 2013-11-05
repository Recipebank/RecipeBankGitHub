package com.rb.testClient;

import com.rb.testClient.LoginStub.LoginAndGetUserInfoResponse;

public class LoginClient {

	// TODO Auto-generated constructor stub
	public static void main(String[] args) throws Exception {
		LoginStub stub = new LoginStub();

		// Creating the request
		com.rb.testClient.LoginStub.LoginAndGetUserInfo request = new com.rb.testClient.LoginStub.LoginAndGetUserInfo();
		request.setUserName("admin");
		request.setPassword("123");

		// Invoking the service
		LoginAndGetUserInfoResponse response = stub.loginAndGetUserInfo(request);
		System.out.println("Response : " + response.get_return());
	}

}
