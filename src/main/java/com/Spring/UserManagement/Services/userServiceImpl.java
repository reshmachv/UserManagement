package com.Spring.UserManagement.Services;

import com.Spring.UserManagement.Model.Entity.User;
import com.Spring.UserManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class userServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUsers(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    @Override
    public User findByName(String UserName) {
        return userRepo.findByuserName(UserName);
    }
}
