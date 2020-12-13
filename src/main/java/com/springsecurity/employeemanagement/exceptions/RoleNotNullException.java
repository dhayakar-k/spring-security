package com.springsecurity.employeemanagement.exceptions;

public class RoleNotNullException extends RuntimeException{
    public RoleNotNullException(String message) {
        super(message);
    }
}
