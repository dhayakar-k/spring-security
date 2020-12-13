package com.springsecurity.employeemanagement.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document(collection = "organization")
public class Organization {

    @Id
    private String id;


    private String organization;

    private String orgBranch;

    private List<Employee> employeeList;

    private boolean status;
}
