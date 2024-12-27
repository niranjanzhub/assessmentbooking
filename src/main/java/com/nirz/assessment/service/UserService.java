package com.nirz.assessment.service;

import com.nirz.assessment.model.User;
import com.nirz.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save a user
    public User saveUser(User user) throws Exception  {
    	 if (userRepository.existsByEmail(user.getEmail())) {
             throw new Exception("Email already exists");
         }
        return userRepository.save(user);
    }

    // Get a user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
