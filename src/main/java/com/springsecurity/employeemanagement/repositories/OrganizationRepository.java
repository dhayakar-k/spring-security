package com.springsecurity.employeemanagement.repositories;

import com.springsecurity.employeemanagement.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {

    @Autowired
    MongoTemplate mongoTemplate = null;

    List<Organization> findByOrganization(String orgName);

    /*@Query("{ 'organization': { $org:?0 } }")
    List<Organization> findByOrganization(String org);*/
}
