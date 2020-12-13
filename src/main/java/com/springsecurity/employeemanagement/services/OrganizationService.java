package com.springsecurity.employeemanagement.services;

import com.springsecurity.employeemanagement.exceptions.RecordNotFoundException;
import com.springsecurity.employeemanagement.models.Organization;
import com.springsecurity.employeemanagement.repositories.OrganizationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Map<String, Object> getPagedData(int pageNo, int pageSize, String sortBy) {
        Map<String, Object> response = new HashMap<>();
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Organization> organizationPage = organizationRepository.findAll(pageable);
        response.put("data", organizationPage.getContent());
        response.put("Total No Of Pages", organizationPage.getTotalPages());
        response.put("Total No Of Elements", organizationPage.getTotalElements());
        response.put("Current Page No ", organizationPage.getNumber());
      //  log.info("Data with the pagination is "+response);
        return response;
    }

    public List<Organization> findAllByExample(Organization org){

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("organization",
                ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.DEFAULT ));
        Example<Organization> organizationExample = Example.of(org, exampleMatcher);
        //Example<Organization> organizationExample = Example.of(org);
        return organizationRepository.findAll(organizationExample);
    }

    public List<Organization> getDataByOrganizationName(String orgName) {

        return organizationRepository.findByOrganization(orgName);
    }

    public List<Organization> getOrgDetailsByEmployeeAge(String age) {
        return organizationRepository.findByOrganization(age);
    }

    public Organization getByOrganizationId(String id) {

        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent()) {
                return organization.get();
        } else {
          //  throw new CustomException( 500 ,new Date(), "Id is not valid", "Please check the id");
            throw new RecordNotFoundException("Record not found with the given id");
        }

    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public List<Organization> saveAll(List<Organization> organizations) {
        return organizationRepository.saveAll(organizations);
    }
}
