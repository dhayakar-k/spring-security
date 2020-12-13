package com.springsecurity.employeemanagement.exceptions;

public class InvalidUserNameAndPasswordException extends RuntimeException{
    public InvalidUserNameAndPasswordException(String message) {
        super(message);
    }
}
