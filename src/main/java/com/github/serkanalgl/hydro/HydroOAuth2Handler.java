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


/**
 *
 * This class consist available methods for the HydroOAuth2
 *
 * @author serkanalgl
 *
 */
@FunctionalInterface
public interface HydroOAuth2Handler {

    Token getToken() throws HydroOAuth2Exception;

}
