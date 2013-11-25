
/**
 * FollowingCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.rb.testClient;

    /**
     *  FollowingCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class FollowingCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public FollowingCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public FollowingCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for setFollowFriend method
            * override this method for handling normal response from setFollowFriend operation
            */
           public void receiveResultsetFollowFriend(
                    com.rb.testClient.FollowingStub.SetFollowFriendResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setFollowFriend operation
           */
            public void receiveErrorsetFollowFriend(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeFollowFriend method
            * override this method for handling normal response from removeFollowFriend operation
            */
           public void receiveResultremoveFollowFriend(
                    com.rb.testClient.FollowingStub.RemoveFollowFriendResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeFollowFriend operation
           */
            public void receiveErrorremoveFollowFriend(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getFollowingList method
            * override this method for handling normal response from getFollowingList operation
            */
           public void receiveResultgetFollowingList(
                    com.rb.testClient.FollowingStub.GetFollowingListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getFollowingList operation
           */
            public void receiveErrorgetFollowingList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkFolllowState method
            * override this method for handling normal response from checkFolllowState operation
            */
           public void receiveResultcheckFolllowState(
                    com.rb.testClient.FollowingStub.CheckFolllowStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkFolllowState operation
           */
            public void receiveErrorcheckFolllowState(java.lang.Exception e) {
            }
                


    }
    