package com.backend.ws.hjemmesalgws.ui.usermodel.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXIST("Record already exist"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELTE_RECORD("Could not delete record"),
    EMAIL_ADDRES_NOT_VERIFIED("Email address could not be verified");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
