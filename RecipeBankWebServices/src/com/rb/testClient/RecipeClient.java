package com.rb.testClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;

import com.rb.util.ProduceJSON;

public class RecipeClient {

	public static void main(String[] args) throws Exception {

		RecipeStub stub = new RecipeStub();

		// Test GetAllRecipes()
		// Creating the request
		// com.rb.testClient.RecipeStub.GetAllRecipes request = new
		// com.rb.testClient.RecipeStub.GetAllRecipes();

		// Invoking the service

		/*
		 * com.rb.testClient.RecipeStub.GetAllRecipesResponse response = stub
		 * .getAllRecipes(request); String jsonString = response.get_return();
		 * 
		 * // get response Json-format string from websrevice
		 * System.out.println("GetAllRecipes JsonString" + jsonString);
		 */

		// // test convert response string to ArrayList.
		// ArrayList<HashMap<String, String>> alArrayList = ProduceJSON
		// .parseJsonArrayToArrylist(jsonString);
		// System.out.println("Converted from json:" + alArrayList.toString());
		// HashMap<String, String> hMap = new HashMap<String, String>();
		// // display the ArrayList which converted by json string.
		// for (int i = 0; i < alArrayList.size(); i++) {
		// hMap = alArrayList.get(i);
		// Set<String> keys = hMap.keySet();
		// Iterator iterator = hMap.keySet().iterator();
		// while (iterator.hasNext()) {
		//
		// String key = iterator.next().toString();
		// System.out.print(key + ":" + hMap.get(key));
		// }
		// System.out.println();
		// }

		// // test covert an arrayList to Json format String.
		// String arrayToJsonString = ProduceJSON
		// .arrayListToJsonArray(alArrayList);
		// System.out.println("ArrayList to Json Result:" + arrayToJsonString);

		// Test GetRecipesAsYouWant(int amount)
		/*
		 * com.rb.testClient.RecipeStub.GetRecipesAsYouWant request1 = new
		 * com.rb.testClient.RecipeStub.GetRecipesAsYouWant();
		 * request1.setAmount(6);
		 * 
		 * // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.GetRecipesAsYouWantResponse response1 =
		 * stub .getRecipesAsYouWant(request1);
		 * System.out.println("Top 6 Recipes Information are :\n" +
		 * response1.get_return());
		 */
		// // test convert a hashmap to one json object
		// HashMap<String, String> hsHashMap = new HashMap<String, String>();
		// hsHashMap.put("username", "admin");
		// hsHashMap.put("password", "123");
		// System.out.println(hsHashMap.toString());
		// System.out.println(ProduceJSON.hashMapToJsonObject(hsHashMap));
		// System.out.println(ProduceJSON.parseJsonObjectToHashMap(ProduceJSON
		// .hashMapToJsonObject(hsHashMap)));

		// Test getRecipeByAccount(int accountId)
		/*
		 * com.rb.testClient.RecipeStub.GetRecipeByAccount request2 = new
		 * com.rb.testClient.RecipeStub.GetRecipeByAccount();
		 * request2.setAccountId(1);
		 * 
		 * // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.GetRecipeByAccountResponse response2 =
		 * stub .getRecipeByAccount(request2);
		 * System.out.println("Recipe of User(AccountId = 1)  :\n" +
		 * response2.get_return());
		 * 
		 * // Test getRecipeByAccountWithAmount(int accountId, int amount)
		 * com.rb.testClient.RecipeStub.GetRecipeByAccountWithAmount request3 =
		 * new com.rb.testClient.RecipeStub.GetRecipeByAccountWithAmount();
		 * request3.setAccountId(1); request3.setAmount(2); // Invoking the
		 * service
		 * 
		 * com.rb.testClient.RecipeStub.GetRecipeByAccountWithAmountResponse
		 * response3 = stub .getRecipeByAccountWithAmount(request3); System.out
		 * .println("Recipe of User(AccountId = 1 & show in 2 record)  :\n" +
		 * response3.get_return());
		 * 
		 * // Test getRecipeDetails(int recipeId)
		 * com.rb.testClient.RecipeStub.GetRecipeDetails request4 = new
		 * com.rb.testClient.RecipeStub.GetRecipeDetails();
		 * request4.setRecipeId(11); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.GetRecipeDetailsResponse response4 =
		 * stub .getRecipeDetails(request4);
		 * System.out.println("Recipe detail(id=11):\n" +
		 * response4.get_return());
		 * 
		 * // Test searchRecipeById(int recipeId)
		 * com.rb.testClient.RecipeStub.SearchRecipeById request5 = new
		 * com.rb.testClient.RecipeStub.SearchRecipeById();
		 * request5.setRecipeId(1); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.SearchRecipeByIdResponse response5 =
		 * stub .searchRecipeById(request5);
		 * System.out.println("Search recipe(Recipeid=1):\n" +
		 * response5.get_return());
		 * 
		 * // Test searchRecipeByCategory(int categoryId)
		 * com.rb.testClient.RecipeStub.SearchRecipeByCategory request6 = new
		 * com.rb.testClient.RecipeStub.SearchRecipeByCategory();
		 * request6.setCategoryId(10); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.SearchRecipeByCategoryResponse response6
		 * = stub .searchRecipeByCategory(request6);
		 * System.out.println("Search recipe(Categoryid=10):\n" +
		 * response6.get_return());
		 * 
		 * // Test searchRecipeByRate(int rate)
		 * com.rb.testClient.RecipeStub.SearchRecipeByRate request7 = new
		 * com.rb.testClient.RecipeStub.SearchRecipeByRate();
		 * request7.setRate(1); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.SearchRecipeByRateResponse response7 =
		 * stub .searchRecipeByRate(request7);
		 * System.out.println("Search recipe(Rate=4):\n" +
		 * response7.get_return());
		 * 
		 * // Test searchRecipeByState(int state)
		 * com.rb.testClient.RecipeStub.SearchRecipeByState request8 = new
		 * com.rb.testClient.RecipeStub.SearchRecipeByState();
		 * request8.setState(0); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.SearchRecipeByStateResponse response8 =
		 * stub .searchRecipeByState(request8); System.out
		 * .println("Search recipe(State=0):\n" + response8.get_return());
		 * 
		 * // Test searchRecipeByKeyWord(String keyword)
		 * com.rb.testClient.RecipeStub.SearchRecipeByKeyWord request9= new
		 * com.rb.testClient.RecipeStub.SearchRecipeByKeyWord();
		 * request9.setKeyword("Salad"); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.SearchRecipeByKeyWordResponse response9
		 * = stub .searchRecipeByKeyWord(request9); System.out
		 * .println("Search recipe(Keyword=\"Salad\"):\n" +
		 * response8.get_return());
		 */
		// Test searchRecipeByRate(int rate)
		/*
		 * com.rb.testClient.RecipeStub.GetRecipeIngredient request10 = new
		 * com.rb.testClient.RecipeStub.GetRecipeIngredient();
		 * request10.setRecipeId(11); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.GetRecipeIngredientResponse response10 =
		 * stub .getRecipeIngredient(request10);
		 * System.out.println("Get Ingredient(RecipeId=11):\n" +
		 * response10.get_return());
		 * 
		 * // rateRecipe(int recipeId, int rate)
		 * com.rb.testClient.RecipeStub.RateRecipe request11 = new
		 * com.rb.testClient.RecipeStub.RateRecipe(); request11.setRecipeId(2);
		 * request11.setRate(3); // Invoking the service
		 * 
		 * com.rb.testClient.RecipeStub.RateRecipeResponse response11 = stub
		 * .rateRecipe(request11); System.out.println("rate recipe(RecipeId=2):"
		 * + response11.get_return());
		 */
		//test InsertRecipeStep
		/*com.rb.testClient.RecipeStub.InsertRecipeStep request = new com.rb.testClient.RecipeStub.InsertRecipeStep();
		JSONObject stepObject = new JSONObject();
		stepObject.put("RecipeId", 12);
		stepObject.put("StepDesc", "adsfasfasfadsf");
		stepObject.put("StepPhoto", "");
		stepObject.put("stepTime", 5.00);
		request.setStepObject(stepObject.toString());
		com.rb.testClient.RecipeStub.InsertRecipeStepResponse response = stub.insertRecipeStep(request);
		System.out.println("Response:"+ response.get_return());*/
		
		//test InsertIngredient
		/*com.rb.testClient.RecipeStub.InsertIngredient request = new com.rb.testClient.RecipeStub.InsertIngredient();
		JSONObject stepObject = new JSONObject();
		stepObject.put("RecipeId", 12);
		stepObject.put("IngredientName", "DouFu");
		stepObject.put("ingredientMeasure", "lb");
		stepObject.put("IngredientQuanlity", 5.00);
		request.setIngredientObject(stepObject.toString());
		com.rb.testClient.RecipeStub.InsertIngredientResponse response = stub.insertIngredient(request);
		System.out.println("Response:"+ response.get_return());*/
		
		//test deleteRecipe
	/*	com.rb.testClient.RecipeStub.DeleteRecipe request = new com.rb.testClient.RecipeStub.DeleteRecipe();
		JSONObject stepObject = new JSONObject();
		stepObject.put("RecipeId", 2);
		request.setJsonObject(stepObject.toString());
		com.rb.testClient.RecipeStub.DeleteRecipeResponse response = stub.deleteRecipe(request);
		System.out.println("Response:"+ response.get_return());*/
		
		//test search recipe by username
		com.rb.testClient.RecipeStub.SearchRecipeByUserName request = new com.rb.testClient.RecipeStub.SearchRecipeByUserName();
		request.setAmount(4);
		request.setUserName("eve");
		
		com.rb.testClient.RecipeStub.SearchRecipeByUserNameResponse response = stub.searchRecipeByUserName(request);
		System.out.println("Response:"+ response.get_return());
	}

}
