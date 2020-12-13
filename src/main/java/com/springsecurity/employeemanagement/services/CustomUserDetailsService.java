package com.springsecurity.employeemanagement.services;

import com.springsecurity.employeemanagement.exceptions.RoleNotNullException;
import com.springsecurity.employeemanagement.exceptions.UserNotFoundException;
import com.springsecurity.employeemanagement.models.User;
import com.springsecurity.employeemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if(!user.isPresent()) {
            throw new UserNotFoundException("User Not Found with the given user name");
        } else if(user.get().getRole() == null) {
            throw new RoleNotNullException("Role Shouldn't be null");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.get().getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(), grantedAuthorities);
    }
}
