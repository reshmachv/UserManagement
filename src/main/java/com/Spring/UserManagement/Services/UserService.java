package com.Spring.UserManagement.Services;

import com.Spring.UserManagement.Model.Entity.User;
import com.Spring.UserManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUsers(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }
    public User findByName(String UserName) {
        return userRepo.findByuserName(UserName);
    }
    public List<User> showUsers() {
        return userRepo.findAll();
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public Boolean checkPassword(String userName, String password) {
        boolean check = passwordEncoder.matches(password, userRepo.findByuserName(userName).getPassword());
        return check;
    }

    public User updateUsers(User currentUser) {
        if (currentUser.getPassword().isEmpty() || currentUser.getPassword().isBlank()) {
            currentUser.setPassword(userRepo.findByuserName(currentUser.getUserName()).getPassword());
        }
        /*else if (!checkPassword(currentUser.getUserName(), currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(currentUser.getPassword()));
        }*/
        else{
            currentUser.setPassword(passwordEncoder.encode(currentUser.getPassword()));
        }
        return userRepo.save(currentUser);
    }
}
