package com.rb.testClient;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShoppingListClient {

	// TODO Auto-generated constructor stub
	public static void main(String[] args) throws Exception {
		/*
		 * com.rb.testClient.RecipeStub.GetRecipesAsYouWant request1 = new
		 * com.rb.testClient.RecipeStub.GetRecipesAsYouWant();
		 * request1.setAmount(6);
		 * 
		 * com.rb.testClient.RecipeStub.GetRecipesAsYouWantResponse response1 =
		 * stub .getRecipesAsYouWant(request1);
		 */

		ShoppingListStub stub = new ShoppingListStub();
		// test add ingredient into shopping list -----start
		// Creating the request
		/*
		 * com.rb.testClient.ShoppingListStub.AddIngredientIntoShoppingList
		 * request = new
		 * com.rb.testClient.ShoppingListStub.AddIngredientIntoShoppingList();
		 * JSONObject ingredientObject=new JSONObject();
		 * ingredientObject.put("IngredientId", 13);
		 * ingredientObject.put("RecipeId", 12);
		 * System.out.println("Json String:"+ingredientObject.toString());
		 * request.setIngredientObject(ingredientObject.toString());
		 * 
		 * // Invoking the service
		 * com.rb.testClient.ShoppingListStub.AddIngredientIntoShoppingListResponse
		 * response = stub .addIngredientIntoShoppingList(request);
		 * System.out.println("Response : " + response.get_return());
		 */
		// test add ingredient into shopping list -----end

		// test view ingredients in the shopping list -----start
		// Creating the request
		com.rb.testClient.ShoppingListStub.ViewShoppingList viewRequest = new com.rb.testClient.ShoppingListStub.ViewShoppingList();
viewRequest.setAccountId(1);
		// Invoking the service
		com.rb.testClient.ShoppingListStub.ViewShoppingListResponse viewResponse = stub
				.viewShoppingList(viewRequest);
		// System.out.println("Response : " + viewResponse.get_return());
		JSONArray jArray = new JSONArray(viewResponse.get_return());
		int rId = -1;
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jObject = jArray.getJSONObject(i);

			int id = Integer.parseInt(jObject.get("RecipeId").toString());
			if (rId != id) {
				System.out.println(jObject.get("RecipeTitle") + " "
						+ jObject.get("RecipeId"));
				System.out.println("-----------"
						+ jObject.get("ShoppingIngredientsId").toString() + ","
						+ jObject.get("IngredientName").toString() + ","
						+ jObject.get("IngredientMeasure").toString() + ","
						+ jObject.get("IngredientQuanlity").toString() + ","
						+ jObject.get("ShoppingIngredientState").toString());
				rId = id;
			} else {
				System.out.println("-----------"
						+ jObject.get("ShoppingIngredientsId").toString() + ","
						+ jObject.get("IngredientName").toString() + ","
						+ jObject.get("IngredientMeasure").toString() + ","
						+ jObject.get("IngredientQuanlity").toString() + ","
						+ jObject.get("ShoppingIngredientState").toString());
			}
		}
		// JSONObject jsonObject=new JSONObject(bean);
		// ShoppingListOperation.addIngredientIntoShoppingList(13, 12);

		// test changeShoppingListState
	/*	com.rb.testClient.ShoppingListStub.ChangeShoppingListState request = new com.rb.testClient.ShoppingListStub.ChangeShoppingListState();
		request.setShoppingIngredientsId(2);
		request.setState(1);
		// Invoking the service
		com.rb.testClient.ShoppingListStub.ChangeShoppingListStateResponse response = stub
				.changeShoppingListState(request);
		System.out.println("Response : " + response.get_return());*/
		
		//test addIngredientsIntoShoppingListByRecipeId 
		com.rb.testClient.ShoppingListStub.AddIngredientsIntoShoppingListByRecipeId request = new com.rb.testClient.ShoppingListStub.AddIngredientsIntoShoppingListByRecipeId();
		request.setAccountId(1);
		request.setRecipeId(12);
		// Invoking the service
		com.rb.testClient.ShoppingListStub.AddIngredientsIntoShoppingListByRecipeIdResponse response = stub
				.addIngredientsIntoShoppingListByRecipeId(request);
		System.out.println("Response : " + response.get_return());
		
	}
}
