package com.backend.ws.hjemmesalgws.ui.usermodel.response;

import java.util.Date;

public class Error_Msg {
    private Date timestamp;
    private String message;

    public Error_Msg(){}

    public Error_Msg(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
