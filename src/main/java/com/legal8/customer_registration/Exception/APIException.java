package com.legal8.customer_registration.Exception;

public class APIException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public APIException(String message){
        super(message);
    }
}
