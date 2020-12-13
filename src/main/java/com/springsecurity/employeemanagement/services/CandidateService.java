package com.springsecurity.employeemanagement.services;

import com.springsecurity.employeemanagement.exceptions.CandidateExistException;
import com.springsecurity.employeemanagement.models.Candidate;
import com.springsecurity.employeemanagement.repositories.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;


    public Candidate save(Candidate candidate) {
        Optional<Candidate> availableCandidate = candidateRepository.findByCandidateFirstNameAndCandidateLastName(
                candidate.getCandidateFirstName(), candidate.getCandidateLastName());
        if (availableCandidate.isPresent()) {
            log.error("There is an Employee with that firstname and lastname ");
            throw new CandidateExistException("Employee already registered");
        }
        return candidateRepository.save(candidate);
    }
}
