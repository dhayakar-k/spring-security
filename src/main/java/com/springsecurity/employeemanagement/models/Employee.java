package com.springsecurity.employeemanagement.models;

import lombok.Data;

@Data
public class Employee {


    private String employeeFirstName;

    private String employeeLastName;

    private int age;

    private EmployeeDesignationDetails employeeDesignationDetails;

    private Address address;


}
