
/**
 * RecipeCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.rb.testClient;

    /**
     *  RecipeCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RecipeCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RecipeCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RecipeCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for searchRecipeById method
            * override this method for handling normal response from searchRecipeById operation
            */
           public void receiveResultsearchRecipeById(
                    com.rb.testClient.RecipeStub.SearchRecipeByIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchRecipeById operation
           */
            public void receiveErrorsearchRecipeById(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRecipeFromFavouriteList method
            * override this method for handling normal response from getRecipeFromFavouriteList operation
            */
           public void receiveResultgetRecipeFromFavouriteList(
                    com.rb.testClient.RecipeStub.GetRecipeFromFavouriteListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecipeFromFavouriteList operation
           */
            public void receiveErrorgetRecipeFromFavouriteList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRecipeByAccount method
            * override this method for handling normal response from getRecipeByAccount operation
            */
           public void receiveResultgetRecipeByAccount(
                    com.rb.testClient.RecipeStub.GetRecipeByAccountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecipeByAccount operation
           */
            public void receiveErrorgetRecipeByAccount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchRecipeByUserName method
            * override this method for handling normal response from searchRecipeByUserName operation
            */
           public void receiveResultsearchRecipeByUserName(
                    com.rb.testClient.RecipeStub.SearchRecipeByUserNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchRecipeByUserName operation
           */
            public void receiveErrorsearchRecipeByUserName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRecipeIngredient method
            * override this method for handling normal response from getRecipeIngredient operation
            */
           public void receiveResultgetRecipeIngredient(
                    com.rb.testClient.RecipeStub.GetRecipeIngredientResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecipeIngredient operation
           */
            public void receiveErrorgetRecipeIngredient(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createRecipe method
            * override this method for handling normal response from createRecipe operation
            */
           public void receiveResultcreateRecipe(
                    com.rb.testClient.RecipeStub.CreateRecipeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createRecipe operation
           */
            public void receiveErrorcreateRecipe(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchRecipeByState method
            * override this method for handling normal response from searchRecipeByState operation
            */
           public void receiveResultsearchRecipeByState(
                    com.rb.testClient.RecipeStub.SearchRecipeByStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchRecipeByState operation
           */
            public void receiveErrorsearchRecipeByState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRecipeByAccountWithAmount method
            * override this method for handling normal response from getRecipeByAccountWithAmount operation
            */
           public void receiveResultgetRecipeByAccountWithAmount(
                    com.rb.testClient.RecipeStub.GetRecipeByAccountWithAmountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecipeByAccountWithAmount operation
           */
            public void receiveErrorgetRecipeByAccountWithAmount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRecipesAsYouWant method
            * override this method for handling normal response from getRecipesAsYouWant operation
            */
           public void receiveResultgetRecipesAsYouWant(
                    com.rb.testClient.RecipeStub.GetRecipesAsYouWantResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecipesAsYouWant operation
           */
            public void receiveErrorgetRecipesAsYouWant(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertRecipeStep method
            * override this method for handling normal response from insertRecipeStep operation
            */
           public void receiveResultinsertRecipeStep(
                    com.rb.testClient.RecipeStub.InsertRecipeStepResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertRecipeStep operation
           */
            public void receiveErrorinsertRecipeStep(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchRecipeByRate method
            * override this method for handling normal response from searchRecipeByRate operation
            */
           public void receiveResultsearchRecipeByRate(
                    com.rb.testClient.RecipeStub.SearchRecipeByRateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchRecipeByRate operation
           */
            public void receiveErrorsearchRecipeByRate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRecipeDetails method
            * override this method for handling normal response from getRecipeDetails operation
            */
           public void receiveResultgetRecipeDetails(
                    com.rb.testClient.RecipeStub.GetRecipeDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRecipeDetails operation
           */
            public void receiveErrorgetRecipeDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rateRecipe method
            * override this method for handling normal response from rateRecipe operation
            */
           public void receiveResultrateRecipe(
                    com.rb.testClient.RecipeStub.RateRecipeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rateRecipe operation
           */
            public void receiveErrorrateRecipe(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllRecipes method
            * override this method for handling normal response from getAllRecipes operation
            */
           public void receiveResultgetAllRecipes(
                    com.rb.testClient.RecipeStub.GetAllRecipesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllRecipes operation
           */
            public void receiveErrorgetAllRecipes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchRecipeByKeyWord method
            * override this method for handling normal response from searchRecipeByKeyWord operation
            */
           public void receiveResultsearchRecipeByKeyWord(
                    com.rb.testClient.RecipeStub.SearchRecipeByKeyWordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchRecipeByKeyWord operation
           */
            public void receiveErrorsearchRecipeByKeyWord(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchRecipeByCategory method
            * override this method for handling normal response from searchRecipeByCategory operation
            */
           public void receiveResultsearchRecipeByCategory(
                    com.rb.testClient.RecipeStub.SearchRecipeByCategoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchRecipeByCategory operation
           */
            public void receiveErrorsearchRecipeByCategory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertIngredient method
            * override this method for handling normal response from insertIngredient operation
            */
           public void receiveResultinsertIngredient(
                    com.rb.testClient.RecipeStub.InsertIngredientResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertIngredient operation
           */
            public void receiveErrorinsertIngredient(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteRecipe method
            * override this method for handling normal response from deleteRecipe operation
            */
           public void receiveResultdeleteRecipe(
                    com.rb.testClient.RecipeStub.DeleteRecipeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteRecipe operation
           */
            public void receiveErrordeleteRecipe(java.lang.Exception e) {
            }
                


    }
    