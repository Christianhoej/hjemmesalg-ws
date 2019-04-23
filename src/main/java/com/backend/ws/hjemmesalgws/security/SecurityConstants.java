package com.backend.ws.hjemmesalgws.security;

import com.backend.ws.hjemmesalgws.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; //4 dage
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    //public static final String TOKEN_SECRET = "jan91gun92chr97"; //anvendes til at kryptere token - Kan sættes tilfældigt
    public static String getTokenSecret(){
        Application_Properties_Reader application_properties_reader = (Application_Properties_Reader) SpringApplicationContext.getBean("Application_Properties_Reader");
        return application_properties_reader.getTokenSecret();
    }
}
