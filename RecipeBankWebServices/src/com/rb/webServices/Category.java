package com.rb.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;

public class Category {

	public String getAllCategories()
	{
		String category="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql="select * from recipebank.category";
		
		try {
			conn=ConnectDB.getConnection();
			st=conn.prepareStatement(sql);
			rs=st.executeQuery();
			category=ProduceJSON.resultSetToJsonArray(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectDB.closeConnection(conn);
		}
		
		return category;
		
	}
	

}
