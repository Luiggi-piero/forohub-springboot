package com.kronos.forohub.validations;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
