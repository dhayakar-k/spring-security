package com.springsecurity.employeemanagement.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeDesignationDto {

    private Double empSalary;

    @NotEmpty(message = "Designation should not be null")
    private String designation;

    private String branch;

    @NotEmpty(message = "Email should not be null")
    @Email(message = "Email should be a valid email")
    private String emailId;

    private Long mobileNumber;

    private Long telephonicNumber;

    @NotEmpty(message = "Bank name should not be null")
    private String bankName;
}
