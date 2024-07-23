package com.financeapp.financeapp.service;

import com.financeapp.financeapp.entity.User;
import com.financeapp.financeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User login(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null && bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return foundUser;
        }
        return null;
    }

    public User updateProfile(String id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setEmail(user.getEmail());
        // other fields to update
        return userRepository.save(existingUser);
    }
}

