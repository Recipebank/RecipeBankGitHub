package com.rb.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rb.util.ConnectDB;

public class ImageOperation {
	//Huijun Sun
	public ImageOperation() {
		// TODO Auto-generated constructor stub
	}
	public int insertImage(String bString)
	{
		int status=0;
		Connection conn = null;
	    PreparedStatement statement = null;
	    
	    System.out.println(bString);
	    try {
			conn=ConnectDB.getConnection();
			String sql = "update  recipebank.recipe set photo=? where Recipeid=1";

			statement = conn.prepareStatement(sql);
			//pstmt.setBinaryStream(1, b, (int) file.length());
			byte[] b=bString.getBytes();
			statement.setBytes(1, b);
			
			
			if(statement.executeUpdate()>0)
			{
				status=1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	    	ConnectDB.closeConnection(conn);
	    }
		return status;
	}

}
