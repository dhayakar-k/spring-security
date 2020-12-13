package com.springsecurity.employeemanagement.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Address {

    private String homeAddress;

    private String state;

    private String city;

    private String pinCode;
}
