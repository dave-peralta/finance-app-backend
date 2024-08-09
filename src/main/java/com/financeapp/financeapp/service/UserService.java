package com.financeapp.financeapp.service;

import com.financeapp.financeapp.entity.User;
import com.financeapp.financeapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword().trim()));
        return userRepository.save(user);
    }

    public User login(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            logger.error("Login failed: Username or password is null");
            throw new IllegalArgumentException("Username and password are required");
        }

        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null) {
            logger.warn("Login failed: User not found for username: {}", user.getUsername());
            throw new IllegalArgumentException("Invalid username or password");
        }

        String reEncodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//        bCryptPasswordEncoder.matches(reEncodedPassword, foundUser.getPassword())
        if (bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            logger.info("Login successful for user: {}", user.getUsername());
            return foundUser;
        } else {
            logger.warn("Login failed: Incorrect password for username: {}", user.getUsername());
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

    public User updateProfile(String id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setEmail(user.getEmail());
        // other fields to update
        return userRepository.save(existingUser);
    }
}

