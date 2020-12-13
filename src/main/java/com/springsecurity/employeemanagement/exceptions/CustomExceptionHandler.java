package com.springsecurity.employeemanagement.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public final ResponseEntity<Object> handleException(CustomException response, WebRequest request) {

     CustomExceptionSchema customException = new CustomExceptionSchema(response.getStatusCode(), response.getTimeStamp(), response.getMessage() ,
             request.getDescription(false));
     return new ResponseEntity<>(customException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ExceptionResponse response = new ExceptionResponse("Server Error", 500, details);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {RecordNotFoundException.class})
    public final ResponseEntity<Object> customException(RecordNotFoundException exception, WebRequest request) {

        List<String> exceptionDetails = new ArrayList<>();
        exceptionDetails.add(exception.getLocalizedMessage());
        exceptionDetails.add("Please check the given id is valid or not");
        ExceptionResponse response = new ExceptionResponse("Record Not Found", 404, exceptionDetails);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
      List<String> exceptionDetails = new ArrayList<>();
      for (ObjectError error: exception.getBindingResult().getAllErrors()) {
          exceptionDetails.add(error.getDefaultMessage());
      }
     ExceptionResponse response = new ExceptionResponse("Validation Failed", 400, exceptionDetails);
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
