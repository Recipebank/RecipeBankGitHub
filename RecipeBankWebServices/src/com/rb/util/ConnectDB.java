package com.rb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public ConnectDB() {
		// TODO Auto-generated constructor stub
	}
	public static Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "recipebank";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "admin";

        Class.forName(driver).newInstance();
        Connection conn = DriverManager.getConnection(url + dbName, userName,password);

        return conn;
    }


    public static void closeConnection(Connection conn) {

    try {

        conn.close();

    } catch (SQLException e) {

    }

}

}
