package com.rb.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;

import org.kobjects.base64.Base64;
//Huijun Sun
public class ImageOperation {

	public void putBytesImg(int num) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/recipebank", "root", "admin");

			Statement stmt = conn.createStatement();

			stmt.close();
			PreparedStatement pstmt = null;
			String sql = "";
			BufferedImage image = ImageIO.read(new File("c://RecipesImage//"
					+ num + ".jpg"));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(image, "jpg", out);
			byte[] b = out.toByteArray();

			sql = "update  recipebank.recipe set photo=? where Recipeid=?";

			pstmt = conn.prepareStatement(sql);
			// pstmt.setBinaryStream(1, b, (int) file.length());
			pstmt.setString(1, Base64.encode(b));
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			System.out.println("finish insert byte[] image to database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getBytesImage(String outfile, int id) throws Exception {
		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			File file = null;
			FileOutputStream fos = null;
			InputStream is = null;
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/recipebank", "root", "admin");
			byte[] Buffer = new byte[4096];

			pstmt = conn
					.prepareStatement("select photo from recipebank.recipe where Recipeid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			file = new File(outfile);
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file);

			// is=new
			// ByteArrayInputStream(Base64.decode(rs.getString("photo")).getBytes("UTF-8"));
			is = new ByteArrayInputStream(Base64.decode(rs.getString("photo")));
			int size = 0;

			while ((size = is.read(Buffer)) != -1) {
				// System.out.println(size);
				fos.write(Buffer, 0, size);
			}
			fos.close();
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block

			
			e.printStackTrace();
		}

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
