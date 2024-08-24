package com.Spring.UserManagement.Controllers;

import com.Spring.UserManagement.Services.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping("/Users")
public class UserControllers {
    @Autowired
    private userServiceImpl userService;
}

