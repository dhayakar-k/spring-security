package com.springsecurity.employeemanagement.controllers;

import com.springsecurity.employeemanagement.exceptions.InvalidUserNameAndPasswordException;
import com.springsecurity.employeemanagement.jwtutil.JwtUtil;
import com.springsecurity.employeemanagement.models.AuthenticationRequest;
import com.springsecurity.employeemanagement.models.User;
import com.springsecurity.employeemanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class UsersCommandController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/generate-token")
    public String generateToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (Exception e) {
            log.error("Invalid User Name or Password");
            throw new InvalidUserNameAndPasswordException("Invalid user name or password");
        }
        log.info("Generated Token "+jwtUtil.generateToken(request.getUserName()));
        return jwtUtil.generateToken(request.getUserName());
    }
    @PostMapping(value = "/sign-up")
    public User singUpNewUser(@Valid @RequestBody User user) {
        return userService.addNewUser(user);
    }
}
