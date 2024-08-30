package com.Spring.UserManagement.Services;

import com.Spring.UserManagement.Model.Entity.User;

import java.util.List;

public interface UserService {
    User saveUsers(User user);

    User findByName(String UserName);

    List<User> showUsers();

    void deleteUser(User user);
}
