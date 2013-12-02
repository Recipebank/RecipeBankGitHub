package com.rb.util;
/*
author:Huijun Sun

*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

public class ShoppingListOperation {

	public static int addIngredientIntoShoppingList(int ingredientId,int recipeId)
	{
		int result=0;
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		String sqlSearch="select * from recipebank.recipeingredientlist where IngredientId=? and recipeid=?;";
		String sqlInsert="insert into recipebank.shoppinglist (RecipeId,IngredientId,ingredientMeasure,IngredientQuanlity)values(?,?,?,?);";
		
		try {
			connection=ConnectDB.getConnection();
			st=connection.prepareStatement(sqlSearch);
			st.setInt(1, ingredientId);
			st.setInt(2, recipeId);
			rs=st.executeQuery();
			if (rs.next()) {
				
				st=connection.prepareStatement(sqlInsert);
				st.setInt(1,rs.getInt("RecipeId"));
				st.setInt(2,rs.getInt("IngredientId"));
				st.setString(3,rs.getString("IngredientMeasure"));
				st.setDouble(4, rs.getDouble("IngredientQuanlity"));
				if(st.executeUpdate()>0)
				{
					result=1;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectDB.closeConnection(connection);
		}
		
		
		return result;
	}
	public static int deleteOneIngredientInShoppingList(int shoppingIngredientId)
	{
		int result=0;
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		String sqlString="delete from recipebank.shoppinglist where ShoppingIngredientsId=?;";
		try {
			connection=ConnectDB.getConnection();
			st=connection.prepareStatement(sqlString);
			st.setInt(1, shoppingIngredientId);
			if(st.executeUpdate()>0)
			{
				result=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectDB.closeConnection(connection); 
		}
		return result;
	}
	public static boolean deleteIngredientsInShoppingList(int[] ids)
	{
		boolean result=false;
		Connection connection = null;
		String sqlString="delete from recipebank.shoppinglist where ShoppingIngredientsId in (";
		try {
			connection=ConnectDB.getConnection();
			Statement statement=connection.createStatement();
			for (int i = 0; i < ids.length; i++) {
				if (i==ids.length-1) {
					sqlString+=ids[i]+")";
				}else {
					sqlString+=ids[i]+",";
				}
			}
			System.out.println(sqlString);
			result=statement.execute(sqlString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectDB.closeConnection(connection); 
		}
		return result;
		
	}
	public static boolean changeShoppingListState(int id, int state)
	{
		boolean result=false;
		Connection connection = null;
		String sql="update recipebank.shoppinglist set ShoppingIngredientState=? where shoppingIngredientsId=?;";
		try {
			connection=ConnectDB.getConnection();
			PreparedStatement st=connection.prepareStatement(sql);
			st.setInt(1, state);
			st.setInt(2, id);
			if(st.executeUpdate()>0)
			{
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectDB.closeConnection(connection); 
		}
		return result;
		
	}
}
