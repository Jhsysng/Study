package com.example.ssecurityjwt.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;
@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private AuthenticationManager a_manager;



    @Value("${jwt.signing.key}")
    private String signingKey;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getHeader("code");

        if(code == null){
            Authentication authentication = new UsernamePasswordAuthentication(username, password);
            a_manager.authenticate(authentication);
        }else{
            Authentication authentication = new OtpAuthentication(username, code);
            authentication = a_manager.authenticate(authentication);

            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

            String jwt = Jwts.builder()
                    .setClaims(Map.of("username", username))
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization", jwt);

        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
