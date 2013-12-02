package com.rb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class AccountOperation {

	public AccountOperation() {
		// TODO Auto-generated constructor stub
	}
	public static int createAccount(HashMap<String, String> accountMap)
	{
		int result=0;
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		String sqlString="insert into recipebank.account (NickName,GmailAddress,Password) values(?,?,?);";
		try {

			connection = ConnectDB.getConnection();
			st = connection.prepareStatement(sqlString);

			st.setString(1, accountMap.get("NickName"));
			st.setString(2, "GmailAddress");
			st.setString(3, accountMap.get("Password"));
			
			if(st.executeUpdate()>0)
			{
				result=1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection);
		}

		return result;
	}
	//author:Huijun Sun
	//paramater: account id which member wants to delete this account in the App
	public static int deleteAccount(int accountId)
	{
		int result=0;
		Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "update recipebank.account set AccountState=1 where accountid=?;";
	    //System.out.println(sql);
	    try {
			conn=ConnectDB.getConnection();
			statement=conn.prepareStatement(sql);
			statement.setInt(1, accountId);
			if(statement.executeUpdate()>0)
			{
				
				if(RecipeOperation.deleteRecipeByAccountId(accountId))
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
	    	ConnectDB.closeConnection(conn);
	    }
		return result;
	}
}
