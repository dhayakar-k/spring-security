package com.springsecurity.employeemanagement.models;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmployeeDesignationDetails {

    private Double empSalary;

    private String designation;

    private String branch;

    private String emailId;

    private Long mobileNumber;

    private Long telephonicNumber;

    private String bankName;
}
