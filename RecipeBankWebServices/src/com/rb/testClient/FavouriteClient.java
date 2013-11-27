package com.rb.testClient;

public class FavouriteClient {
	
	public static void main(String[] args) throws Exception {

		// Test getFavouriteListByAccount(int accountId)
		FavouriteStub stub = new FavouriteStub();
		com.rb.testClient.FavouriteStub.GetFavouriteListByAccount request = new com.rb.testClient.FavouriteStub.GetFavouriteListByAccount();
		request.setAccountId(1);
		com.rb.testClient.FavouriteStub.GetFavouriteListByAccountResponse response = stub
				.getFavouriteListByAccount(request);
		System.out.println("Get Favourite List By AccountId = 1: "
				+ response.get_return());

		// Test getFavouriteListByRecipe(int recipeId)
		com.rb.testClient.FavouriteStub.GetFavouriteListByRecipe request1 = new com.rb.testClient.FavouriteStub.GetFavouriteListByRecipe();
		request1.setRecipeId(3);
		com.rb.testClient.FavouriteStub.GetFavouriteListByRecipeResponse response1 = stub
				.getFavouriteListByRecipe(request1);
		System.out.println("Get Favourite List By RecipeId = 3: "
				+ response1.get_return());

		// Test checkFavouriteState(int accountId, int recipedId)
		com.rb.testClient.FavouriteStub.CheckFavouriteState request2 = new com.rb.testClient.FavouriteStub.CheckFavouriteState();
		request2.setRecipeId(6);;
		request2.setAccountId(1);
		com.rb.testClient.FavouriteStub.CheckFavouriteStateResponse response2 = stub
				.checkFavouriteState(request2);
		System.out.println("Check Favourite List By AccountId = 1, RecipedId = 6: "
				+ response2.get_return());

		// Test addFavourite(int accountId, int recipedId)
		com.rb.testClient.FavouriteStub.AddFavourite request3 = new com.rb.testClient.FavouriteStub.AddFavourite();
		request3.setRecipeId(4);
		request3.setAccountId(2);
		com.rb.testClient.FavouriteStub.AddFavouriteResponse response3 = stub
				.addFavourite(request3);
		System.out.println("Add Favourite to accountId=2, recipeId=4: "
				+ response3.get_return());
		
		// Test removeFavourite(int accountId, int recipeId)
		com.rb.testClient.FavouriteStub.RemoveFavourite request4= new com.rb.testClient.FavouriteStub.RemoveFavourite();
		request4.setAccountId(3);
		request4.setRecipeId(7);
		com.rb.testClient.FavouriteStub.RemoveFavouriteResponse response4 = stub
				.removeFavourite(request4);
		System.out.println("Remove favourite to accountId=3, recipeId=7: "
				+ response4.get_return());

	}

}
