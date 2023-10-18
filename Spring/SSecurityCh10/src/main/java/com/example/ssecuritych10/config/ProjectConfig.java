package com.example.ssecuritych10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService uds(){
        var uds = new InMemoryUserDetailsManager();
        var u1 = User.withUsername("john")
                .password("12345")
                .authorities("READ")
                .build();

        uds.createUser(u1);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(c->{
            CorsConfigurationSource source = request -> {
                var cors = new CorsConfiguration();
                cors.setAllowedOrigins(
                        List.of("http://localhost:8080"));
                cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                return cors;
            };
        });
        http.csrf().disable();

        http.authorizeRequests()
                .anyRequest().permitAll();

        return http.build();
    }
}
