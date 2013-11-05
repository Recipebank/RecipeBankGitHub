package com.rb.testClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.rb.util.ProduceJSON;

public class RecipeClient {

	public static void main(String[] args) throws Exception {

		RecipeStub stub = new RecipeStub();

		// Creating the request
		com.rb.testClient.RecipeStub.GetAllRecipes request = new com.rb.testClient.RecipeStub.GetAllRecipes();
		// request.setUserName("admin");
		// request.setPassword("123");

		// Invoking the service

		com.rb.testClient.RecipeStub.GetAllRecipesResponse response = stub.getAllRecipes(request);
		String jsonString = response.get_return();

		// get response Json-format string from websrevice
		System.out.println("Original JsonString" + jsonString);

		// test convert response string to ArrayList.
		ArrayList<HashMap<String, String>> alArrayList = ProduceJSON
				.parseJsonArrayToArrylist(jsonString);
		System.out.println("Converted from json:" + alArrayList.toString());
		HashMap<String, String> hMap = new HashMap<String, String>();
		// display the ArrayList which converted by json string.
		for (int i = 0; i < alArrayList.size(); i++) {
			hMap = alArrayList.get(i);
			Set<String> keys = hMap.keySet();
			Iterator iterator = hMap.keySet().iterator();
			while (iterator.hasNext()) {

				String key = iterator.next().toString();
				System.out.print(key + ":" + hMap.get(key));
			}
			System.out.println();
		}

		// test covert an arrayList to Json format String.
		String arrayToJsonString = ProduceJSON
				.arrayListToJsonArray(alArrayList);
		System.out.println("ArrayList to Json Result:" + arrayToJsonString);

//		// test convert a hashmap to one json object
//		HashMap<String, String> hsHashMap = new HashMap<String, String>();
//		hsHashMap.put("username", "admin");
//		hsHashMap.put("password", "123");
//		System.out.println(hsHashMap.toString());
//		System.out.println(ProduceJSON.hashMapToJsonObject(hsHashMap));
//		System.out.println(ProduceJSON.parseJsonObjectToHashMap(ProduceJSON
//				.hashMapToJsonObject(hsHashMap)));
	}

}
