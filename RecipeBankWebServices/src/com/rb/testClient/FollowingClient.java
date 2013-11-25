package com.rb.testClient;

public class FollowingClient {

	public static void main(String[] args) throws Exception {

		// Test checkFolllowState(int accountId, int friendId)
		FollowingStub stub = new FollowingStub();
		com.rb.testClient.FollowingStub.CheckFolllowState request = new com.rb.testClient.FollowingStub.CheckFolllowState();
		request.setAccountId(1);
		request.setFriendId(2);
		com.rb.testClient.FollowingStub.CheckFolllowStateResponse response = stub
				.checkFolllowState(request);
		System.out.println("Check Following State(accountid=1, friendid=2)"
				+ response.get_return());

		// Test setFollowFriend(int accountId, int friendId)
		com.rb.testClient.FollowingStub.SetFollowFriend request1 = new com.rb.testClient.FollowingStub.SetFollowFriend();
		request1.setAccountId(1);
		request1.setFriendId(2);
		com.rb.testClient.FollowingStub.SetFollowFriendResponse response1 = stub
				.setFollowFriend(request1);
		System.out.println("set Following friend(accountid=1, friendid=2)"
				+ response1.get_return());

		// Test removeFollowFriend(int accountId, int friendId)
		com.rb.testClient.FollowingStub.RemoveFollowFriend request3 = new com.rb.testClient.FollowingStub.RemoveFollowFriend();
		request3.setAccountId(4);
		request3.setFriendId(1);
		com.rb.testClient.FollowingStub.RemoveFollowFriendResponse response3 = stub
				.removeFollowFriend(request3);
		System.out.println("remove Following friend(accountid=4, friendid=1)"
				+ response3.get_return());

		// Test getFollowingList(int accountId)
		com.rb.testClient.FollowingStub.GetFollowingList request4 = new com.rb.testClient.FollowingStub.GetFollowingList();
		request4.setAccountId(2);
		com.rb.testClient.FollowingStub.GetFollowingListResponse response4 = stub
				.getFollowingList(request4);
		System.out.println("get Following friend(accountid=2)"
				+ response4.get_return());

	}

}
