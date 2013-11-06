package com.rb.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;

public class Login {

	public String loginGetStringTypeStatus(String userName, String password) {
		String status = "";
		Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM recipebank.account where nickname='"+userName+"' and password='"+password+"'";
	    
	    try {
			conn=ConnectDB.getConnection();
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			if(rs.next())
			{
				status="Connection Success!";
			}
			else
			{
				status="Connection Failed!";
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
	public int loginGetIntTypeStatus(String userName, String password) {
		int status = 0;
		Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM recipebank.account where nickname='"+userName+"' and password='"+password+"'";
	    
	    try {
			conn=ConnectDB.getConnection();
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			if(rs.next())
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
	public boolean loginGetBoolenTypeStatus(String userName, String password) {
		boolean status = false;
		Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM recipebank.account where nickname='"+userName+"' and password='"+password+"'";
	    
	    try {
			conn=ConnectDB.getConnection();
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			if(rs.next())
			{
				status=true;
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
	public String loginAndGetUserInfo(String userName,String password)
	{
		String userInfoString="";
		Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM recipebank.account where nickname='"+userName+"' and password='"+password+"'";
	    System.out.println(sql);
	    try {
			conn=ConnectDB.getConnection();
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			userInfoString=ProduceJSON.resultSetToJsonArray(rs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	    	ConnectDB.closeConnection(conn);
	    }
		return userInfoString;
	}
}
