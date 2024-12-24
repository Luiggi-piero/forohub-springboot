package com.kronos.forohub.repository;

import com.kronos.forohub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<User, Long> {
    // el nombre de usuario es el email
    UserDetails findByEmail(String email);
}
