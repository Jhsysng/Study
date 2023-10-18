package com.example.ssecuritych7.config;

import com.example.ssecuritych7.config.security.AuthenticationLoggingFilter;
import com.example.ssecuritych7.config.security.CsrfTokenLogger;
import com.example.ssecuritych7.config.security.RequestValidationFilter;
import com.example.ssecuritych7.config.security.StaticKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        var manger = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

        manger.createUser(user1);
        manger.createUser(user2);

        return manger;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll();


        return http.build();
    }
}
