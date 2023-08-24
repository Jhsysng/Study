package com.example.ssecuritych3.component;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider {
    implements A`uthenticationProvider {


        @Override
        public boolean supports(Class<?> authenticationType){
            return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
        }
    }
}
