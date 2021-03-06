package com.rb.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;
import com.rb.util.RecipeOperation;

public class Favourite {

	public String getFavouriteListByAccount(int accountId) {

		String result = "";
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			ResultSet rs = null;
			// String sql =
			// "SELECT f.RecipeId, r.RecipeTitle, r.Rate, r.RecipeState,r.Photo FROM recipebank.favourite f inner join recipebank.recipe r on f.RecipeId = r.RecipeId where f.AccountId = "
			// + accountId;
			String sql = "SELECT f.RecipeId, r.RecipeTitle, r.Rate, r.RecipeState,r.Photo FROM recipebank.favourite f inner join recipebank.recipe r on f.RecipeId = r.RecipeId where r.recipeState=0 and f.AccountId = "
					+ accountId;
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			result = ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}
		return result;
	}
//
	public String getFavouriteListByRecipe(int recipeId) {

		String result = "";
		Connection conn = null;
		if (RecipeOperation.checkRecipeState(recipeId)) {

			try {
				conn = ConnectDB.getConnection();
				PreparedStatement st = null;
				ResultSet rs = null;
				String sql = "SELECT f.RecipeId, r.RecipeTitle, r.Rate, r.RecipeState,r.Photo FROM recipebank.favourite f inner join recipebank.recipe r on f.RecipeId = r.RecipeId where f.RecipeId ="
						+ recipeId;
				st = conn.prepareStatement(sql);
				rs = st.executeQuery();
				result = ProduceJSON.resultSetToJsonArray(rs);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnection(conn);
			}
		}
		
		return result;
	}

	public int checkFavouriteState(int accountId, int recipeId) {

		int result = 0;
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			ResultSet rs = null;
			String sql = "select * from recipebank.favourite where AccountId = "
					+ accountId + " and RecipeId = " + recipeId;
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			if (!rs.next()) {
				result = 0;
			} else {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}

		return result;
	}

	public int addFavourite(int accountId, int recipeId) {

		int result = 0;
		Connection conn = null;
		
		if (checkFavouriteState(accountId, recipeId) == 1&&!RecipeOperation.checkRecipeState(recipeId)) {
			result = 3;
			return result;
		}
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			String sql = "insert into recipebank.favourite (AccountId,RecipeId) values(?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, accountId);
			st.setInt(2, recipeId);
			st.executeUpdate();
			if (checkFavouriteState(accountId, recipeId) == 1) {
				result = 1;
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

	public int removeFavourite(int accountId, int recipeId) {

		int result = 0;
		Connection conn = null;
		if (checkFavouriteState(accountId, recipeId) == 0&&!RecipeOperation.checkRecipeState(recipeId)) {
			result = 3;
			return result;
		}
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			String sql = "delete from recipebank.favourite where AccountId = ? and RecipeId = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, accountId);
			st.setInt(2, recipeId);
			st.executeUpdate();
			if (checkFavouriteState(accountId, recipeId) == 0) {
				result = 1;
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
