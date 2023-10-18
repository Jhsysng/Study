package com.example.ssecuritych11.controller;

import com.example.ssecuritych11.data.entity.Otp;
import com.example.ssecuritych11.data.entity.User;
import com.example.ssecuritych11.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    private final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user){
        logger.info("addUser: " + user.getUsername());
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody User user){
        userService.auth(user);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response){
        if (userService.check(otp)){
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
