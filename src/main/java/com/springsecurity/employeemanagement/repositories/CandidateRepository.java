package com.springsecurity.employeemanagement.repositories;

import com.springsecurity.employeemanagement.models.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

    Optional<Candidate> findByCandidateFirstNameAndCandidateLastName(String candidateFirstName, String candidateLastName);
}
