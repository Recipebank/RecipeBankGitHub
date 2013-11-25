package com.rb.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rb.util.ConnectDB;
import com.rb.util.ProduceJSON;

public class Following {

	//Dongchao Feng
	public int checkFolllowState(int accountId, int friendId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			ResultSet rs = null;
			String sql = "select * from recipebank.followlist where AccountId="
					+ accountId + " and FriendId=" + friendId
					+ " and FollowState=0";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			if (!rs.next()) {
				result = 0;
			} else {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnection(conn);
		}

		return result;
	}

	public String getFollowingList(int accountId) {
		String result = "";
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			ResultSet rs = null;
			String sql = "select * from recipebank.followlist where AccountId="
					+ accountId + " and FollowState=0";
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

	public int setFollowFriend(int accountId, int friendId) {
		int result = 0;
		Connection conn = null;
		if (checkFolllowState(accountId, friendId) == 1) {
			result = 3;
			return result;
		}
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			String sql = "insert into recipebank.followlist (AccountId,FriendId,FollowState)values(?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, accountId);
			st.setInt(2, friendId);
			st.setInt(3, 0);
			st.executeUpdate();
			if (checkFolllowState(accountId, friendId) == 1) {
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

	public int removeFollowFriend(int accountId, int friendId) {
		int result = 0;
		Connection conn = null;
		if (checkFolllowState(accountId, friendId) == 0) {
			result = 3;
			return result;
		}
		try {
			conn = ConnectDB.getConnection();
			PreparedStatement st = null;
			String sql = "delete from recipebank.followlist where AccountId=? and FriendId = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, accountId);
			st.setInt(2, friendId);
			st.executeUpdate();
			if (checkFolllowState(accountId, friendId) == 0) {
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
