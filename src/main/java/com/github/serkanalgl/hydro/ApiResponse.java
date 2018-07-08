/**
 * Copyright (c) 2018, Serkan Algül. All rights reserved
 * <p>
 * This code is free software and distributed in the hope that it will be useful; you can use it and/or modify it.
 * <p>
 * Hydro is the blockchain division of Hydrogen, the global financial platform of the Web 3.0.
 * Hydro enables private financial systems to seamlessly leverage the public blockchain.
 * <p>
 * The Hydro API enables applications to interface with Hydro’s smart contracts.
 * All Hydrogen APIs are built on REST principles, with resource oriented URLs and HTTP response codes.
 * All API responses are returned in JSON format.
 * <p>
 * This library helps people to authorize on Hydrogen APIs. Raindrop, Snowflake etc..
 * <p>
 * Token is expires in 24 hours. getToken method is renew token if expired.
 * <p>
 * Example usage:
 * <p>
 * HydroOAuth2 hydroOuth2 = new HydroOAuth2Builder()
 * .setClientId("your client id")
 * .setClientSecret("your secret key")
 * .environment(Environment.PRODUCTION)  //Either SANDBOX or PRODUCTION ( if you don't call environment method, default: SANDBOX )
 * .build();
 * <p>
 * Token token = hydroOuth2.getToken().get();
 */
package com.github.serkanalgl.hydro;

/**
 *
 * Base api response bean
 *
 * @author serkanalgl
 *
 */
public class ApiResponse {

    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
