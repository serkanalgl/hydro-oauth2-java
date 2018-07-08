# Java library for Hydro OAuth2
<img src="https://github.com/serkanalgl/hydro-oauth2-java/blob/master/hydro_logo.png">

## Introduction

<p>This library helps people to authorize on Hydrogen APIs. Raindrop, Snowflake etc..</p>

## Dependency

- unirest-java 1.4.9


## Compilation

- Java 1.8
- Maven 3


## Installation

### Recommended

Add below dependency into your pom.xml

```
<dependency>
    <groupId>com.github.serkanalgl</groupId>
    <artifactId>hydro-oauth2-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Manual

You can also install manually:

```shell
git clone https://github.com/serkanalgl/hydro-oauth2-java.git
cd hydro-oauth2-java
mvn clean install
```

## Create HydroOAuth2 Instance

```java
  HydroOAuth2 hydroOAuth2 = new HydroOAuth2Builder()
                .environment(Environment.PRODUCTION))
                .setClientId("my client id")
                .setClientSecret("my client secret")
                .build();
```

`HydroOAuth2Builder parameters`

  - `environment` (default: SANDBOX): `Environment.SANDBOX` | `Environment.PRODUCTION` to set your environment
  - `clientId` (required): Your OAuth id for the Hydro API
  - `clientSecret` (required): Your OAuth secret for the Hydro API



## Get Token

```java
  Token token = hydroOAuth2.getToken();
```


- automatically refreshes OAuth token.
- can throw HydroOAuth2Exception: if not authenticated or hydro api call error.

- `Token properties`
    - `accessToken` : OAuth token that will be used for all subsequent API calls
    - `tokenType`: Always will be bearer
    - `expriesIn`: When the token expires in seconds and will need to be called again. Default is 86400.
    - `scope`: The scope your user has been granted in the application
    - `apps`: Always will be hydro

- `Authorization on HYDRO APIs`
    - Should be send as header "Authorization" : "Bearer " + token.getAccessToken()


## Contact

If you have any further question/suggestion/issue, do not hesitate to contact me.

serkanalgl@gmail.com


## Donate

Ethereum 0x575c1776c7812450ba6f91be117c9c092ae7acb7


## Copyright

Copyright (c) 2018, Under MIT licence Serkan Algül. All rights reserved.
