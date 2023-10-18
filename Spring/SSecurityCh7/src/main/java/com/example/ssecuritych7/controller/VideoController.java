package com.example.ssecuritych7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class VideoController {

    @GetMapping("/video/{country}/{language}")
    public String video(@PathVariable String country, @PathVariable String language){
        return "Video allowed for " + country + " and " + language;
    }
}

