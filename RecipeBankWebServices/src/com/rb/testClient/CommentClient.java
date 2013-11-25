package com.rb.testClient;

public class CommentClient {

	public static void main(String[] args) throws Exception {

		// Test getCommentbyRecipe(int recipeId)
		CommentStub stub = new CommentStub();
		com.rb.testClient.CommentStub.GetCommentbyRecipe request = new com.rb.testClient.CommentStub.GetCommentbyRecipe();
		request.setRecipeId(1);
		com.rb.testClient.CommentStub.GetCommentbyRecipeResponse response = stub
				.getCommentbyRecipe(request);
		System.out.println("get comment by recipeId = 1"
				+ response.get_return());

		// Test getCommentbyAccount(int accountId)
		com.rb.testClient.CommentStub.GetCommentbyAccount request1 = new com.rb.testClient.CommentStub.GetCommentbyAccount();
		request1.setAccountId(2);
		com.rb.testClient.CommentStub.GetCommentbyAccountResponse response1 = stub
				.getCommentbyAccount(request1);
		System.out.println("get comment by accountId = 2"
				+ response1.get_return());

		// Test setComment(int accountId, int recipeId, String info)
		com.rb.testClient.CommentStub.SetComment request2 = new com.rb.testClient.CommentStub.SetComment();
		request2.setAccountId(1);
		request2.setRecipeId(6);
		request2.setInfo("Very well");
		com.rb.testClient.CommentStub.SetCommentResponse response2 = stub
				.setComment(request2);
		System.out.println("set comment by accountId = 1"
				+ response2.get_return());

		// Test removeComment(int commentId)
		com.rb.testClient.CommentStub.RemoveComment request3 = new com.rb.testClient.CommentStub.RemoveComment();
		request3.setCommentId(8);
		com.rb.testClient.CommentStub.RemoveCommentResponse response3 = stub
				.removeComment(request3);
		System.out.println("remove comment by commentId = 7"
				+ response3.get_return());
	}

}
