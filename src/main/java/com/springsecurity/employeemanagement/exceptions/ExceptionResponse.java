package com.springsecurity.employeemanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class ExceptionResponse {

    private String message;

    private int statusCode;

    private List<String> details;
}
