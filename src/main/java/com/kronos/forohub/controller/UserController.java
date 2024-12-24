package com.kronos.forohub.controller;

import com.kronos.forohub.dto.UserAutentication;
import com.kronos.forohub.infra.security.DataJWTToken;
import com.kronos.forohub.infra.security.TokenService;
import com.kronos.forohub.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    // dispara el proceso de autenticacion en spring
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid UserAutentication userAutentication){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userAutentication.email(), userAutentication.password()
        );

        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generarToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(JWTToken));
    }
}
