package com.rb.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;

public class Comment {

	public String getCommentbyRecipe(int recipeId) {

		String result = "";
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM recipebank.comment where RecipeId ="
					+ recipeId;
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

	public String getCommentbyAccount(int accountId) {

		String result = "";
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM recipebank.comment where AccountId ="
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

	public int setComment(int accountId, int recipeId, String info) {

		int result = 0;
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			String sql = "insert into recipebank.comment (DetailInfo,RecipeId,AccountId) values(?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, info);
			st.setInt(2, recipeId);
			st.setInt(3, accountId);
			int re = st.executeUpdate();
			if (re==1) {
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

	public int removeComment(int commentId) {

		int result = 0;
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			String sql = "delete from recipebank.comment where CommentId=?";
			st = conn.prepareStatement(sql);
			st.setInt(1, commentId);
			int re = st.executeUpdate();
			if (re==1) {
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
