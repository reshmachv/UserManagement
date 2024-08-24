package com.Spring.UserManagement.Controllers;

import com.Spring.UserManagement.Model.Entity.User;
import com.Spring.UserManagement.Model.Reponse.SignupResponse;
import com.Spring.UserManagement.Services.UserDetailsServiceImpl;
import com.Spring.UserManagement.Services.UserService;
import com.Spring.UserManagement.Services.userServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;

@RestController
@Component
@RequestMapping("/signUp")
public class SignupController {
    @Autowired
    private userServiceImpl userService;

    @PostMapping()
    public ResponseEntity<SignupResponse> signUpUser(@RequestBody @Valid User user){
        SignupResponse response = SignupResponse.builder()
                .user(userService.saveUsers(user))
                .message("Sign Up Complete!")
                .timestamp(Instant.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SignupResponse handlePasswordValidationException(MethodArgumentNotValidException e) {

        //Returning password error message as a response.
        return SignupResponse.builder()
                .message(String.join(",", e.getBindingResult().getFieldError().getDefaultMessage()))
                .timestamp(Instant.now())
                .build();
    }

    @GetMapping()
    public ResponseEntity<?>getcheck(User user){
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
