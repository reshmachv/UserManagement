package com.Spring.UserManagement.Model.Entity;

import com.Spring.UserManagement.Annotation.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NonNull
    @Column(unique = true,length = 15)
    private String userName;
    @Email
    @Column(unique = true)
    private String emailId;
    @NonNull
    @NotBlank(message="Please Mention your role!")
    @Column
    private String role;
    @NonNull
    @NotBlank(message="password can't be blank!")
    @Column
    @Password
    private String password;
}
