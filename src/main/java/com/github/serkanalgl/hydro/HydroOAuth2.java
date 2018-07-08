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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.*;

/**
 *
 * This class is implementation of the HydroOAuth2Handler interface. It can be throw HydroOAuth2Exception for all methods.
 *
 * @author serkanalgl
 *
 */
public final class HydroOAuth2 implements HydroOAuth2Handler {

    private static final Logger logger = LoggerFactory.getLogger(HydroOAuth2.class);

    private String clientId;
    private String clientSecret;
    private Environment environment;

    private Token token;

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> schedule;

    public HydroOAuth2(String clientId, String clientSecret, Environment environment) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.environment = environment;

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        logger.info("HydroOAuth2 initialized for {} usage.", environment);

    }

    @Override
    public Token getToken() throws HydroOAuth2Exception {

        if (token != null) {
            return token;
        }

        try {
            return fetchToken().get();
        } catch (InterruptedException e) {
            throw new HydroOAuth2Exception("unexpected error", e);
        } catch (ExecutionException e) {
            throw new HydroOAuth2Exception("unexpected error", e);
        }
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public Environment getEnvironment() {
        return environment;
    }

    private CompletableFuture<Token> fetchToken() {

        try {

            if (schedule != null) {
                schedule.cancel(false);
            }

            logger.debug("fetching Hydro OAuth2 token.");

            HttpResponse<Token> response = Unirest.post(String.format(getHydroOuth2ApiUrl(), "oauth/token?grant_type=client_credentials"))
                    .basicAuth(this.clientId, this.clientSecret)
                    .asObject(Token.class);

            if (response.getStatus() == 200) {

                token = response.getBody();
                schedule = scheduler.schedule(this::fetchToken, token.getExpriesIn() - 300, TimeUnit.SECONDS);
                logger.debug("Hydro OAuth2 token fetched and scheduled for renew.");
                return CompletableFuture.completedFuture(token);

            } else {
                throw new HydroOAuth2Exception("Error fetching Hydro OAuth2 token. Check that your credentials were entered correctly, and try again. status:" + response.getStatus());
            }
        } catch (UnirestException e) {
            throw new HydroOAuth2Exception("The Hydro Api call failed." + e.getMessage());
        }

    }

    private String getHydroOuth2ApiUrl() {
        if (environment.equals(Environment.SANDBOX)) {
            return "https://sandbox.hydrogenplatform.com/authorization/v1/%s";
        } else {
            return "https://api.hydrogenplatform.com/authorization/v1/%s";
        }
    }

}
