package com.api.users.exceptionhandler;

public class UserInternalServerErrorException extends RuntimeException{
    public UserInternalServerErrorException(String message) {
        super(message);
    }
}
