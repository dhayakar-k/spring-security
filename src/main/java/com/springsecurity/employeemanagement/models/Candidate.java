package com.springsecurity.employeemanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "candidate_registration")
@AllArgsConstructor
public class Candidate {

    @Id
    private String loginId;

    private String candidateFirstName;

    private String candidateLastName;

    private String candidateRole;

    private String candidateState;

    private String routingKey;

    private String hasAdharProof;

}
