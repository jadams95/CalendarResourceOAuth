package com.jaddy.calendarresourceoauth.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @Value("${spring.application.name}")
    String applicationName;

    @GetMapping("/api")
    public String testOnline(){
        return "application is online:\t" + applicationName;
    }

    @GetMapping
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }

}