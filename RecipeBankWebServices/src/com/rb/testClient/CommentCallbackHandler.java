
/**
 * CommentCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.rb.testClient;

    /**
     *  CommentCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CommentCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CommentCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CommentCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for removeComment method
            * override this method for handling normal response from removeComment operation
            */
           public void receiveResultremoveComment(
                    com.rb.testClient.CommentStub.RemoveCommentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeComment operation
           */
            public void receiveErrorremoveComment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCommentbyAccount method
            * override this method for handling normal response from getCommentbyAccount operation
            */
           public void receiveResultgetCommentbyAccount(
                    com.rb.testClient.CommentStub.GetCommentbyAccountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCommentbyAccount operation
           */
            public void receiveErrorgetCommentbyAccount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setComment method
            * override this method for handling normal response from setComment operation
            */
           public void receiveResultsetComment(
                    com.rb.testClient.CommentStub.SetCommentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setComment operation
           */
            public void receiveErrorsetComment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCommentbyRecipe method
            * override this method for handling normal response from getCommentbyRecipe operation
            */
           public void receiveResultgetCommentbyRecipe(
                    com.rb.testClient.CommentStub.GetCommentbyRecipeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCommentbyRecipe operation
           */
            public void receiveErrorgetCommentbyRecipe(java.lang.Exception e) {
            }
                


    }
    