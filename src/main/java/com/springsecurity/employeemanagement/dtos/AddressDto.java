package com.springsecurity.employeemanagement.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressDto {

    @NotEmpty(message = "Home Address is mandatory")
    private String homeAddress;

    private String state;

    private String city;

    @NotEmpty(message = "Pin Code is must")
    private String pinCode;
}
