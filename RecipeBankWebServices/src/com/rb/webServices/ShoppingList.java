package com.rb.webServices;

//owner: Huijun Sun
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.axis2.databinding.utils.Converter;
import org.json.JSONArray;
import org.json.JSONException;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;
import com.rb.util.ShoppingListOperation;

public class ShoppingList {
	public String addIngredientIntoShoppingList(String ingredientObject,
			String accountId) {
		String resultString = null;
		HashMap<String, String> ingredientMap = ProduceJSON
				.parseJsonObjectToHashMap(ingredientObject);
		if (ShoppingListOperation.addIngredientIntoShoppingList(
				Integer.parseInt(ingredientMap.get("IngredientId")),
				Integer.parseInt(ingredientMap.get("RecipeId")),
				Integer.parseInt(accountId)) > 0) {
			resultString = "Success!";
		} else {
			resultString = "Failed!";
		}
		return resultString;
	}
	public String addIngredientsIntoShoppingListByRecipeId(int recipeId,int accountId)
	{
		
		return ShoppingListOperation.checkShoppingList(recipeId, accountId);
		
	}

	public String deleteIngredientFromShoppingList(String ingredientsArray) {
		String resultString = null;
		JSONArray iArray;
		try {
			iArray = new JSONArray(ingredientsArray);
			int[] ids = new int[iArray.length()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.parseInt(iArray.getJSONObject(i)
						.get("ShoppingIngredientsId").toString());
			}
			if (ShoppingListOperation.deleteIngredientsInShoppingList(ids)) {
				resultString = "Success!";
			} else {
				resultString = "Failed!";
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultString;
	}

	public String viewShoppingList(int accountId) {
		String resultString = "";
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement st = null;
		String sqlString = "select ShoppingIngredientsId,s.RecipeId,r.RecipeTitle,i.IngredientName,"
				+ "ShoppingIngredientState,IngredientMeasure,IngredientQuanlity from recipebank.shoppinglist s "
				+ "join recipebank.recipe r on s.RecipeId = r.RecipeId "
				+ "join recipebank.ingredient i on s.IngredientId=i.IngredientId "
				+ "where s.accountId=? "
				+ "order by s.RecipeId,shoppingIngredientsId asc;";
		try {
			connection = ConnectDB.getConnection();
			st = connection.prepareStatement(sqlString);
			st.setInt(1, accountId);
			rs = st.executeQuery();
			resultString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}
		return resultString;
	}

	public String changeShoppingListState(int shoppingIngredientsId, int state) {
		boolean result = false;
		System.out.println("shoppingIngredientsId" + shoppingIngredientsId);
		System.out.println("state" + state);
		if (ShoppingListOperation.changeShoppingListState(
				shoppingIngredientsId, state)) {
			result = true;
		}
		return result + "";
	}

}
