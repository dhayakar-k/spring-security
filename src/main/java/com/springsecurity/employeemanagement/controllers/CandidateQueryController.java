package com.springsecurity.employeemanagement.controllers;

import com.springsecurity.employeemanagement.models.Candidate;
import com.springsecurity.employeemanagement.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates-list")
public class CandidateQueryController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping
    public Page<Candidate> allCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable);
    }
}
