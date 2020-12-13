package com.springsecurity.employeemanagement.controllers;


import com.springsecurity.employeemanagement.models.Organization;
import com.springsecurity.employeemanagement.repositories.OrganizationRepository;
import com.springsecurity.employeemanagement.services.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
@Slf4j
public class OrganizationQueryController {

    private  final OrganizationRepository organizationRepository;

    private final OrganizationService organizationService;

    @GetMapping("/page")
    public Map<String, Object> getDataInPagination(@RequestParam(name = "pageNo",defaultValue = "0") int pageNo
     , @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
                                                   @RequestParam(name = "sortBy",defaultValue = "id") String sortBy){
        return organizationService.getPagedData(pageNo, pageSize, sortBy);
    }

    @GetMapping("/example")
    public List<Organization> getAllByExample(@RequestBody Organization organization){
        return organizationService.findAllByExample(organization);
    }

    @GetMapping("/findByOrgName")
    public List<Organization> getByOrganization(@RequestParam("orgName") String orgName){

        return organizationService.getDataByOrganizationName(orgName);
    }

    @GetMapping("/getByAge/{org}")
    public List<Organization> getAllByAge(@PathVariable("org") String org){
        return organizationService.getOrgDetailsByEmployeeAge(org);
    }
    @GetMapping("/{orgId}")
    public Organization getOrganizationById(@PathVariable String orgId) {

        return organizationService.getByOrganizationId(orgId);
    }
}
