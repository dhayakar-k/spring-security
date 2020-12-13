package com.springsecurity.employeemanagement.controllers;


import com.springsecurity.employeemanagement.config.ConfigProperties;
import com.springsecurity.employeemanagement.exceptions.CandidateRoutingKeyNotNullException;
import com.springsecurity.employeemanagement.models.Candidate;
import com.springsecurity.employeemanagement.repositories.CandidateRepository;
import com.springsecurity.employeemanagement.services.CandidateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register-candidates")
@AllArgsConstructor
@Slf4j
public class CandidateCommandController {

    private final CandidateRepository candidateRepository;

    private final RabbitTemplate rabbitTemplate;

    private final ConfigProperties configProperties;

    private final CandidateService candidateService;

    @PostMapping(value = "/save-candidate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Candidate storeDetails(@RequestBody Candidate candidate) {

       if(candidate.getRoutingKey() != null) {

           if(candidate.getRoutingKey().equals(configProperties.getAdminRoutingKey())) {
               rabbitTemplate.convertAndSend(configProperties.getExchangeName(), configProperties.getAdminRoutingKey(),
                       candidate);
               log.info("Message successfully published to the TSQueue with the payLoad "+candidate);
           } else if(candidate.getRoutingKey().equals(configProperties.getMarketingRoutingKey())){
               rabbitTemplate.convertAndSend(configProperties.getExchangeName(), configProperties.getMarketingRoutingKey(),
                        candidate);
               log.info("Message successfully published to the APQueue with the payLoad "+candidate);
           } else {
               rabbitTemplate.convertAndSend(configProperties.getExchangeName(), configProperties.getFinanceRoutingKey(),
                       candidate);
               log.info("Message successfully published to the MHQueue with the payLoad "+candidate);
           }
          return candidateService.save(candidate);
       }else {
           throw  new CandidateRoutingKeyNotNullException("Routing Key Shouldn't be null");
       }
    }
}
