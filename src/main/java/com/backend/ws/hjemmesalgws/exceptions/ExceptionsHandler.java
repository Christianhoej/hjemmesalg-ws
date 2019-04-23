package com.backend.ws.hjemmesalgws.exceptions;

import com.backend.ws.hjemmesalgws.service.UserService;
import com.backend.ws.hjemmesalgws.ui.usermodel.response.Error_Msg;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {

 /*   @ExceptionHandler(value={UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException e, WebRequest request){

        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); //Her kan "e.getMessage" også customizes -> evt. returner hele e.
    }
    */
 @ExceptionHandler(value={UserServiceException.class})
 public ResponseEntity<Object> handleUserServiceException(UserServiceException e, WebRequest request){

     Error_Msg msg = new Error_Msg(new Date(), e.getMessage());

     return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); //Her kan "e.getMessage" også customizes -> evt. returner hele e.
 }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception e, WebRequest request){

        Error_Msg msg = new Error_Msg(new Date(), e.getMessage());

        return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); //Her kan "e.getMessage" også customizes -> evt. returner hele e.
    }
}
