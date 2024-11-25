package com.bridgelabz.service;

import com.bridgelabz.model.User;
import com.bridgelabz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(int userId) {
        return userRepo.findById(userId).get();
    }
}
