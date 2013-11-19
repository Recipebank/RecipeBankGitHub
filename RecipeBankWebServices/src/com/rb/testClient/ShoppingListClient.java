package com.rb.testClient;

import org.json.JSONObject;

import com.rb.testClient.ShoppingListStub.AddIngredientIntoShoppingListResponse;
import com.rb.util.ShoppingListOperation;

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

		// Creating the request
		com.rb.testClient.ShoppingListStub.AddIngredientIntoShoppingList request = new com.rb.testClient.ShoppingListStub.AddIngredientIntoShoppingList();
		JSONObject ingredientObject=new JSONObject();
		ingredientObject.put("IngredientId", 13);
		ingredientObject.put("RecipeId", 12);
		System.out.println("Json String:"+ingredientObject.toString());
		request.setIngredientObject(ingredientObject.toString());

		// Invoking the service
		com.rb.testClient.ShoppingListStub.AddIngredientIntoShoppingListResponse response = stub
				.addIngredientIntoShoppingList(request);
		System.out.println("Response : " + response.get_return());
		// JSONObject jsonObject=new JSONObject(bean);
		//ShoppingListOperation.addIngredientIntoShoppingList(13, 12);
	}

}
