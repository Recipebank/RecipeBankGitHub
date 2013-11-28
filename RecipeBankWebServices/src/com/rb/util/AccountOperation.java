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

}
