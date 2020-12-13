package com.springsecurity.employeemanagement.exceptions;


public class InvalidOrganizationException extends RuntimeException {

    private static final long serialVersionId = 1L;

    public InvalidOrganizationException(String message) {
        super(message);
    }

    public InvalidOrganizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
