package com.springsecurity.employeemanagement;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.employeemanagement.controllers.UsersCommandController;
import com.springsecurity.employeemanagement.models.User;
import com.springsecurity.employeemanagement.models.enums.Role;
import com.springsecurity.employeemanagement.repositories.UserRepository;
import com.springsecurity.employeemanagement.services.UserService;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestUsers {

    @InjectMocks
    private UsersCommandController usersCommandController;

    private MockMvc mockMvc;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersCommandController).build();
    }

    @Test
    public void addUserTest() throws Exception {

        User user = new User();
        user.setUserName("john doe");
        user.setPassword("doe123");
        user.setRole(Role.EDITOR);
        String userString = objectMapper.writeValueAsString(user);
        when(userService.addNewUser(any(User.class))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sign-up").content(userString).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
    }
}
