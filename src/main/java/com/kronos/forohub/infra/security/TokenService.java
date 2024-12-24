package com.kronos.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kronos.forohub.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}") // propiedad de application.properties
    private String apiSecret;

    public String generarToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generateExpirationDate() {
        // retorna la fecha de expiracion, en 2 horas, zona horaria de sudamerica
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token){
        DecodedJWT verifier = null;
        if(token == null){
            throw new RuntimeException();
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Foro Hub")
                    // reusable verifier instance
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            System.out.println(exception.toString());
        }

        if(verifier.getSubject() == null) {
            throw new RuntimeException("Verifier inválido");
        }
        return verifier.getSubject();
    }
}
