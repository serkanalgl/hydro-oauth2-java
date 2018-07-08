/**
 * Copyright (c) 2018, Under MIT licence Serkan Algül. All rights reserved
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
 * HydroOAuth2 hydroOAuth2 = new HydroOAuth2Builder()
 * .setClientId("your client id")
 * .setClientSecret("your secret key")
 * .environment(Environment.PRODUCTION)  //Either SANDBOX or PRODUCTION ( if you don't call environment method, default: SANDBOX )
 * .build();
 * <p>
 * Token token = hydroOAuth2.getToken();
 */

package com.github.serkanalgl.hydro;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * This class consists fields for mapping response of "oauth/token" endpoint
 *
 * accessToken  Token that will be used for all subsequent API calls
 * tokenType    Always will be bearer
 * expriesIn    When the token expires in seconds and will need to be called again. Default is 86400 or 24 hours.
 * scope        The scope your user has been granted in the application
 * apps         The name of the app. example: hydro
 *
 * @author serkanalgl
 *
 */
public class Token extends ApiResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private long expriesIn;

    private String jti;
    private String scope;
    private String apps;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpriesIn() {
        return expriesIn;
    }

    public void setExpriesIn(long expriesIn) {
        this.expriesIn = expriesIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getApps() {
        return apps;
    }

    public void setApps(String apps) {
        this.apps = apps;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expriesIn=" + expriesIn +
                ", jti='" + jti + '\'' +
                ", scope='" + scope + '\'' +
                ", apps='" + apps + '\'' +
                '}';
    }
}
