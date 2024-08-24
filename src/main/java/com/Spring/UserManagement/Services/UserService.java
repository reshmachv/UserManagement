package com.Spring.UserManagement.Services;

import com.Spring.UserManagement.Model.Entity.User;

public interface UserService {
    User saveUsers(User user);

    User findByName(String UserName);
}
