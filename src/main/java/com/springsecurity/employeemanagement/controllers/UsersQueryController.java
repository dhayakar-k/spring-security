package com.springsecurity.employeemanagement.controllers;

import com.springsecurity.employeemanagement.models.User;
import com.springsecurity.employeemanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/all-users")
public class UsersQueryController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.allUsersInfo(pageable);
    }

}
