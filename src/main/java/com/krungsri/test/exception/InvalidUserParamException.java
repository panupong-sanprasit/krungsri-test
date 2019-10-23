package com.krungsri.test.exception;

public class InvalidUserParamException extends RuntimeException {
    
    public InvalidUserParamException(String msg){
        super(msg);
    }
    
}
