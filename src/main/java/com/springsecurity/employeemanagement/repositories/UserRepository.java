package com.springsecurity.employeemanagement.repositories;

import com.springsecurity.employeemanagement.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> , PagingAndSortingRepository<User, String> {
    Optional<User> findByUserName(String userName);
}
