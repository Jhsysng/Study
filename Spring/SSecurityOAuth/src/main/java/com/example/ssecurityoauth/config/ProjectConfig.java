package com.example.ssecurityoauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Value("${oauth.github.id}")
    private String clientId;
    @Value("${oauth.github.secret}")
    private String clientSecret;

    @Bean
    public ClientRegistrationRepository clientRepository(){
        var c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }
    private ClientRegistration clientRegistration(){
        return CommonOAuth2Provider.GITHUB
                .getBuilder("github")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin();

        return http.build();
    }
}
