package com.kronos.forohub.infra.security;

import com.kronos.forohub.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtener el token del header
        var authHeader = request.getHeader("Authorization");

        if(authHeader != null){
            var token = authHeader.replace("Bearer ", "");
            var userName = tokenService.getSubject(token);

            if(userName != null){
                var user = userRepository.findByEmail(userName);// obtener el usuario de la bd
                // setear un contexto autenticado, forzar el incio de sesion -> entonces para las
                // demás request ya estará autenticado
                // por lo tanto  en SecurityConfigurations.java las .anyRequest().authenticated() estarán autenticadas
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
