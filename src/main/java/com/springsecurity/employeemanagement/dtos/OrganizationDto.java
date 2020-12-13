package com.springsecurity.employeemanagement.dtos;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrganizationDto {

    private String id;


    @NotEmpty(message = "Organization name should not empty")
    private String organization;

    private String orgBranch;

    @NotEmpty(message = "organization should have at least one employee")
    private List<EmployeeDto> employeeList;
}
