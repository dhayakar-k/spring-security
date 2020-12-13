package com.springsecurity.employeemanagement.services;

import com.springsecurity.employeemanagement.models.User;
import com.springsecurity.employeemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
         Optional<User> user = userRepository.findByUserName(userName);
         if(!user.isPresent() ) {
             throw new UsernameNotFoundException("User Not Found with user Name");
         }
         return null;
    }
}
