package com.Spring.UserManagement.Controllers;

import com.Spring.UserManagement.Model.Entity.User;
import com.Spring.UserManagement.Services.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/Users")
public class UserControllers {
    @Autowired
    private userServiceImpl userService;

    @GetMapping("/all-Users")
    public ResponseEntity<List<User>>showAllUsers(){
       List usersList= userService.showUsers();
       return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/{Name}")
    public ResponseEntity<?>findByName(@PathVariable String Name){
        User user=userService.findByName(Name);
        if(user==null){
            return new ResponseEntity<>("User don't exist!",HttpStatus.OK);
        }
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }


    @PutMapping ("/updateUser")
    public ResponseEntity<?>updateUser(@RequestBody User user){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User currentUser=userService.findByName(auth.getName());
        currentUser.setPassword(currentUser.getPassword());
        currentUser.setEmailId(!user.getEmailId().isEmpty()&!user.getEmailId().isBlank()?user.getEmailId(): currentUser.getEmailId());
        currentUser.setRole(!user.getRole().isBlank()&!user.getRole().isEmpty()?user.getRole(): currentUser.getRole());
        userService.updateUsers(currentUser);
        return new ResponseEntity<>(currentUser,HttpStatus.OK);
    }

    @DeleteMapping("/{Name}")
    public ResponseEntity<?>DeleteUser(@PathVariable String Name){
        User user=userService.findByName(Name);
        if(user==null){
            return new ResponseEntity<>("User don't exist!",HttpStatus.OK);
        }
        userService.deleteUser(user);
        return new ResponseEntity<>("User deleted Successfully!",HttpStatus.OK);
    }

    @GetMapping("/checkPassword/{userName}/{password}")
    public ResponseEntity<Boolean> checkpassword(@PathVariable String userName,@PathVariable String password){
        boolean result=userService.checkPassword(userName,password);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }










}

