package com.rb.webServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;
import com.rb.util.RecipeOperation;

public class Recipe {

	String recipeString = "";
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	// Create by Dongchao Feng
	public String getAllRecipes() {
		String sql = "select * from recipebank.recipe where RecipeState = 0";
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;

	}

	// Create by Dongchao Feng
	public String getRecipeByAccount(int accountId) {
		String recipeString = "";
		String sql = "select RecipeId,RecipeTitle,Description,rate,RecipeState,a.AccountId,NickName from recipebank.recipe r join recipebank.account a on r.AccountID=a.AccountId where r.RecipeState = 0 and a.AccountId="
				+ accountId + " order by RecipeId desc";
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;

	}

	// Create by Dongchao Feng
	public String getRecipeByAccountWithAmount(int accountId, int amount) {
		String recipeString = "";
		String sql = "select RecipeId,RecipeTitle,Description,rate,RecipeState,a.AccountId,NickName "
				+ "from recipebank.recipe r join recipebank.account a on r.AccountID=a.AccountId where r.RecipeState = 0 and a.AccountId="
				+ accountId + " order by RecipeId desc limit " + amount;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;

	}
	// anthor: Huijun Sun
	public String getRecipesAsYouWant(int amount) {
		String recipeString = "";
		String sqlString = "select a.AccountId,NickName,RecipeId,RecipeTitle,Description,rate,RecipeState,photo "
				+ "from recipebank.recipe r join recipebank.account a on r.AccountID=a.AccountId "
				+ "where recipeState=0 "
				+ "order by RecipeId desc limit "
				+ amount;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sqlString);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;
	}

	// Create by Dongchao Feng
	public String getRecipeDetails(int recipeId) {

		String sql = "select * from recipebank.recipestep s inner join recipebank.recipe r on s.RecipeId = r.RecipeId where r.RecipeState=0 and s.RecipeId="
				+ recipeId;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;
	}

	// Create by Dongchao Feng
	public String getRecipeFromFavouriteList(int recipeId) {
		String sql = "select * from recipebank.recipe where RecipeState=0 and RecipeId ="
				+ recipeId;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}

		return recipeString;
	}

	// Create by Dongchao Feng
	public String searchRecipeById(int recipeId) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where recipe.RecipeState=0 and recipe.RecipeId="
				+ recipeId;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}

		return recipeString;
	}

	// Create by Dongchao Feng
	public String searchRecipeByCategory(int categoryId) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where recipe.RecipeState=0 and category.CategoryId="
				+ categoryId;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;
	}

	// Create by Dongchao Feng
	public String searchRecipeByRate(int rate) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where  recipe.RecipeState=0 and recipe.Rate="
				+ rate;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;
	}

	// Create by Dongchao Feng
	public String searchRecipeByState(int state) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where recipe.RecipeState="
				+ state;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;
	}

	// Create by Dongchao Feng
	public String searchRecipeByKeyWord(String keyword) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where  recipe.RecipeState=0 and recipe.RecipeTitle LIKE '%"
				+ keyword
				+ "%'or recipe.Description LIKE '%"
				+ keyword
				+ "%' or category.CategoryTitle LIKE '%"
				+ keyword
				+ "%' order by recipe.RecipeId";
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return recipeString;
	}

	// Create by Dongchao Feng
	public String getRecipeIngredient(int recipeId) {
		String sql = "select recipe.RecipeId,recipe.RecipeTitle,a.IngredientId,a.ingredientMeasure,a.IngredientQuanlity,b.IngredientName from recipebank.recipe inner join recipebank.recipeingredientlist a on recipe.RecipeId = a.RecipeId inner join recipebank.ingredient b on a.ingredientId = b.ingredientId where  recipe.RecipeState=0 and recipe.RecipeId="
				+ recipeId;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			recipeString = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}

		return recipeString;
	}

	// anthor: Huijun Sun
	public String CreateRecipe(String recipeString) {
		JSONObject recipeJsonObject = new JSONObject();
		HashMap<String, String> recipeMap = ProduceJSON
				.parseJsonObjectToHashMap(recipeString);
		try {
			recipeJsonObject.put("RecipeId",
					RecipeOperation.createRecipe(recipeMap));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recipeJsonObject.toString();
	}

/*	// anthor: Huijun Sun
	public String insertIngredients(String ingStr) {
		JSONObject jsonObject = new JSONObject();
		ArrayList<HashMap<String, String>> ingList = ProduceJSON
				.parseJsonArrayToArrylist(ingStr);
		try {
			if (RecipeOperation.InsertIngredients(ingList)) {

				jsonObject.put("result", 1);

			} else {
				jsonObject.put("result", 0);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toString();
	}*/
	//author:Huijun Sun
	public String insertIngredient(String ingredientObject) {
		String result="Failed!";
		try {
			if (RecipeOperation.InsertIngredient(ProduceJSON.parseJsonObjectToHashMap(ingredientObject))) {

				result="Success!";

			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*// anthor: Huijun Sun
	public String insertRecipeSteps(String stepString) {
		JSONObject jsonObject = new JSONObject();
		ArrayList<HashMap<String, String>> stepsList = ProduceJSON
				.parseJsonArrayToArrylist(stepString);
		try {
			if (RecipeOperation.insertSteps(stepsList)) {

				jsonObject.put("result", 1);

			} else {
				jsonObject.put("result", 0);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toString();

	}*/
	//anthor: Huijun Sun
	public String insertRecipeStep(String stepObject) {
		String resultString="failed!";

		try {
			if (RecipeOperation.insertStep(ProduceJSON.parseJsonObjectToHashMap(stepObject))) {
				
				resultString="Success!";

			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;

	}
	// Dongchao Feng
	public int rateRecipe(int recipeId, int rate) {
		int result = 0;
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			String sql = "update recipebank.recipe set Rate = ? where RecipeState=0 and  RecipeId=?";
			st = conn.prepareStatement(sql);
			st.setInt(1, rate);
			st.setInt(2, recipeId);
			st.executeUpdate();
			sql = "select * from recipebank.recipe where Rate = ? and RecipeId=?";
			st = conn.prepareStatement(sql);
			st.setInt(1, rate);
			st.setInt(2, recipeId);
			rs = st.executeQuery();
			if (rs.next()) {
				result = rate;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}

		return result;

	}

}
