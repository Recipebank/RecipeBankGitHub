package com.rb.webServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;

public class Recipe {

	String recipeString = "";
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	public String getAllRecipes() {
		String sql = "select * from recipebank.recipe";
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

	public String getRecipeByAccount(int accountId) {
		String recipeString = "";
		String sql = "select RecipeId,RecipeTitle,Description,rate,RecipeState,a.AccountId,NickName from recipebank.recipe r join recipebank.account a on r.AccountID=a.AccountId where a.AccountId="
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

	public String getRecipeByAccountWithAmount(int accountId, int amount) {
		String recipeString = "";
		String sql = "select RecipeId,RecipeTitle,Description,rate,RecipeState,a.AccountId,NickName "
				+ "from recipebank.recipe r join recipebank.account a on r.AccountID=a.AccountId where a.AccountId="
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

	public String getRecipesAsYouWant(int amount) {
		String recipeString = "";
		String sqlString = "select RecipeId,RecipeTitle,Description,rate,RecipeState,a.AccountId,NickName "
				+ "from recipebank.recipe r join recipebank.account a on r.AccountID=a.AccountId "
				+ "order by RecipeId desc limit " + amount;
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

	public String getRecipeDetails(int recipeId) {

		String sql = "select * from recipebank.recipestep  where RecipeId="
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

	public String searchRecipeById(int recipeId) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where recipe.RecipeId="
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

	public String searchRecipeByCategory(int categoryId) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where category.CategoryId="
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

	public String searchRecipeByRate(int rate) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where recipe.Rate="
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

	public String searchRecipeByKeyWord(String keyword) {
		String sql = "select * from recipe inner join recipecategory on recipe.RecipeId = recipecategory.RecipeId inner join category on recipecategory.CategoryId = category.CategoryId where recipe.RecipeTitle LIKE '%"
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

}
