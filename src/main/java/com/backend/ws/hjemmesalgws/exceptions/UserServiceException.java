package com.backend.ws.hjemmesalgws.exceptions;

public class UserServiceException extends RuntimeException{

    private static final long serialversionUID = 10024214352L;

    public UserServiceException(String message){

        super(message);
    }
}
