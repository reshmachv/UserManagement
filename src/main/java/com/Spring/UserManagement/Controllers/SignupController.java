package com.Spring.UserManagement.Controllers;

import com.Spring.UserManagement.Model.Entity.User;
import com.Spring.UserManagement.Model.Reponse.SignupResponse;
import com.Spring.UserManagement.Services.userServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@Component
@RequestMapping("/signUp")
public class SignupController {
    @Autowired
    private userServiceImpl userService;

    @GetMapping()
    public ResponseEntity<?>Healthcheck(User user){
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> signUpUser(@RequestBody @Valid User user){
        if(userService.findByName(user.getUserName())!=null) {
            SignupResponse response= new SignupResponse("user already exist!", Instant.now(),userService.findByName(user.getUserName()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
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

}
