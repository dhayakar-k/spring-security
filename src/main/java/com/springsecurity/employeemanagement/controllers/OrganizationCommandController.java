package com.springsecurity.employeemanagement.controllers;


import com.springsecurity.employeemanagement.config.ConfigProperties;
import com.springsecurity.employeemanagement.exceptions.InvalidOrganizationException;
import com.springsecurity.employeemanagement.exceptions.RecordNotFoundException;
import com.springsecurity.employeemanagement.models.Organization;
import com.springsecurity.employeemanagement.repositories.OrganizationRepository;
import com.springsecurity.employeemanagement.services.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organizationDetails")
@Slf4j
public class OrganizationCommandController {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @Autowired
    private   OrganizationRepository employeeRepository;

    @Autowired
    private  ConfigProperties configProperties;

    @Autowired
    private   OrganizationService organizationService;

    @Autowired
    private  ModelMapper mapper;
    @PostMapping("/saveOrganization")
    public Organization saveOrganization(@Valid @RequestBody  Organization organization){
       if(organization.getOrganization() != null) {
           //Organization organization = mapper.map(organizationDto, Organization.class);
           log.info("publishing a message to rabbitmq server "+organization);
           rabbitTemplate.convertAndSend(configProperties.getExchangeName(), configProperties.getMyRoutingKey(),
                   organization);
           return organizationService.save(organization);
       }else {
           throw new RecordNotFoundException("Organization name is mandatory");
       }
    }

    @PostMapping("/saveAllOrganizations")
    public List<Organization> saveAllOrganizations(@RequestBody List<Organization> organizations){

        if(organizations != null) {
            log.info("List of collections with the details " + organizations);
            rabbitTemplate.convertAndSend(configProperties.getExchangeName(), configProperties.getMyRoutingKey(),
                    organizations);
            log.info("Publishing the list of objects to rabbitmq server");
            return organizationService.saveAll(organizations);
        } else {
            throw new InvalidOrganizationException("Organizations should not empty it should contain at least one organization");
        }
    }
}
