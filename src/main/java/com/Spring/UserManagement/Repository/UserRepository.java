package com.Spring.UserManagement.Repository;

import com.Spring.UserManagement.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByuserName(String UserName);
}
