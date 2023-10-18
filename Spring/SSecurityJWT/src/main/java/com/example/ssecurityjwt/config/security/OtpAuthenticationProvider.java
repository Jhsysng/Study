package com.example.ssecurityjwt.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        boolean result = proxy.sendOtp(username, password);

        if(result){
            return new OtpAuthentication(username, password);
        }else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(OtpAuthentication.class);
    }
}
