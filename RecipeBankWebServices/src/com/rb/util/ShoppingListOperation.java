package com.rb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
