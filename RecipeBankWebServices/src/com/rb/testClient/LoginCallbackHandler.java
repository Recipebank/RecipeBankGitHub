
/**
 * LoginCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.rb.testClient;

    /**
     *  LoginCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class LoginCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public LoginCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public LoginCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for loginGetIntTypeStatus method
            * override this method for handling normal response from loginGetIntTypeStatus operation
            */
           public void receiveResultloginGetIntTypeStatus(
                    com.rb.testClient.LoginStub.LoginGetIntTypeStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loginGetIntTypeStatus operation
           */
            public void receiveErrorloginGetIntTypeStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for loginAndGetUserInfo method
            * override this method for handling normal response from loginAndGetUserInfo operation
            */
           public void receiveResultloginAndGetUserInfo(
                    com.rb.testClient.LoginStub.LoginAndGetUserInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loginAndGetUserInfo operation
           */
            public void receiveErrorloginAndGetUserInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for loginGetStringTypeStatus method
            * override this method for handling normal response from loginGetStringTypeStatus operation
            */
           public void receiveResultloginGetStringTypeStatus(
                    com.rb.testClient.LoginStub.LoginGetStringTypeStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loginGetStringTypeStatus operation
           */
            public void receiveErrorloginGetStringTypeStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for loginGetBoolenTypeStatus method
            * override this method for handling normal response from loginGetBoolenTypeStatus operation
            */
           public void receiveResultloginGetBoolenTypeStatus(
                    com.rb.testClient.LoginStub.LoginGetBoolenTypeStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from loginGetBoolenTypeStatus operation
           */
            public void receiveErrorloginGetBoolenTypeStatus(java.lang.Exception e) {
            }
                


    }
    