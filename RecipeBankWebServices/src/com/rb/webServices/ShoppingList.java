package com.rb.webServices;

import java.util.HashMap;

import com.rb.util.ProduceJSON;
import com.rb.util.ShoppingListOperation;

public class ShoppingList {
	public String addIngredientIntoShoppingList(String ingredientObject)
	{
		String resultString=null;
		HashMap<String, String> ingredientMap=ProduceJSON.parseJsonObjectToHashMap(ingredientObject);
		if(ShoppingListOperation.addIngredientIntoShoppingList(Integer.parseInt(ingredientMap.get("IngredientId")), Integer.parseInt(ingredientMap.get("RecipeId")))>0)
		{
			resultString="Success!";
		}
		else {
			resultString="Failed!";
		}
		return resultString;
	}


}
