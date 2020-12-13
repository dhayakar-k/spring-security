package com.springsecurity.employeemanagement.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeDto {

    @NotEmpty(message = "Employee first name is mandatory")
    private String employeeFirstName;

    @NotEmpty(message = "Employee last name is mandatory")
    private String employeeLastName;

    private int age;

    private EmployeeDesignationDto employeeDesignationDetails;

    private AddressDto address;
}
