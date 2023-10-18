package com.example.ssecuritych6.service;

import com.example.ssecuritych6.data.entity.User;
import com.example.ssecuritych6.data.repository.UserRepository;
import com.example.ssecuritych6.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException(
                        "Problem during authentication!");

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(s);

        return new CustomUserDetails(user);
    }
}
