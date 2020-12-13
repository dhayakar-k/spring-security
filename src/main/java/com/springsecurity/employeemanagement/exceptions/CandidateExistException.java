package com.springsecurity.employeemanagement.exceptions;

public class CandidateExistException extends RuntimeException{
    public CandidateExistException(String message) {
        super(message);
    }
}
