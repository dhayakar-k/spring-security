package com.springsecurity.employeemanagement.services;

import com.springsecurity.employeemanagement.exceptions.UserExistException;
import com.springsecurity.employeemanagement.models.User;
import com.springsecurity.employeemanagement.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addNewUser(User user) {
        Optional<User> existingUser = userRepository.findByUserName(user.getUsername());
        if(existingUser.isPresent()) {
            throw new UserExistException("There is an account with that user name");
        }
        log.info("Adding the new User registration");
        return userRepository.save(user);
    }

    public Page<User> allUsersInfo(Pageable pageable) {
    return userRepository.findAll(pageable);
    }
}
