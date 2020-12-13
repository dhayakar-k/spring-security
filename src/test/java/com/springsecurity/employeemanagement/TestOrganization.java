package com.springsecurity.employeemanagement;

import com.springsecurity.employeemanagement.config.ConfigProperties;
import com.springsecurity.employeemanagement.controllers.OrganizationCommandController;
import com.springsecurity.employeemanagement.models.Address;
import com.springsecurity.employeemanagement.models.Employee;
import com.springsecurity.employeemanagement.models.EmployeeDesignationDetails;
import com.springsecurity.employeemanagement.models.Organization;
import com.springsecurity.employeemanagement.repositories.OrganizationRepository;
import com.springsecurity.employeemanagement.services.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestOrganization {

    @InjectMocks
    private OrganizationCommandController organizationCommandController;

    private MockMvc mockMvc;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private OrganizationRepository employeeRepository;

    @Mock
    private ConfigProperties configProperties;

    @Mock
    private   OrganizationService organizationService;
   /* @Autowired
    private WebApplicationContext applicationContext;*/

    ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setUp() {
       // MockitoAnnotations.initMocks(this);
       mockMvc = MockMvcBuilders.standaloneSetup(organizationCommandController).build();
    }

    @Test
    public void addOrganizationTest() throws Exception{

        Organization organization = new Organization();
        organization.setOrganization("TCS");
        organization.setOrgBranch("Chennai");
        organization.setStatus(true);
        Employee employee = new Employee();
        List<Employee> employeeList = new ArrayList<>();
        employee.setEmployeeFirstName("Dhayakar");
        employee.setEmployeeLastName("Kummari");
        employee.setAge(19);
        employeeList.add(employee);
        EmployeeDesignationDetails designationDetails = new EmployeeDesignationDetails();
        designationDetails.setDesignation("Software Developer");
        designationDetails.setEmpSalary(24578.90);
        designationDetails.setBankName("ICICI");
        designationDetails.setBranch("Hyd");
        designationDetails.setEmailId("dhayakar@digitaldots.ai");
        designationDetails.setMobileNumber(Long.valueOf("9801239033"));
        designationDetails.setTelephonicNumber(Long.valueOf("07890123"));
        Address address = new Address();
        address.setCity("Hyd");
        address.setHomeAddress("3-182, gpd, Veepangandla");
        address.setPinCode("509105");
        address.setState("Telangana");
        employee.setEmployeeDesignationDetails(designationDetails);
        employee.setAddress(address);
        organization.setEmployeeList(employeeList);
        String jsonString = objectMapper.writeValueAsString(organization);
        when(organizationService.save(any(Organization.class))).thenReturn(organization);
        mockMvc.perform(MockMvcRequestBuilders.post("/organizationDetails/saveOrganization")
                              .content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                              .andReturn();
    }
    @Test
   public void contextLoads() {
    }
}
