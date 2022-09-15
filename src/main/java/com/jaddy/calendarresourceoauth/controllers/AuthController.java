package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.service.authservices.ManagerTokenService;
import com.jaddy.calendarresourceoauth.service.authservices.TokenService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import static java.util.Arrays.stream;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

//    private NoOpPasswordEncoder noOpPasswordEncoder;

    private final TokenService tokenService;

    private final ManagerTokenService managerTokenService;


    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    public AuthController(TokenService tokenService, ManagerTokenService managerTokenService) {
        this.tokenService = tokenService;
        this.managerTokenService = managerTokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requred for user has details: '{}'", authentication.getDetails());
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }

    @PreAuthorize("hasAuthority('manager:create')")
    @PostMapping("/tokenMnger")
    public String tokenMnger(Authentication authentication){
        LOG.info("Token requred for user has details: '{}'", authentication.getPrincipal());
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = managerTokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Customer registerUser){
       Customer savedUser =  new Customer(12315L, registerUser.getUsername(), "{noop}" + registerUser.getPassword(), Role.ROLE_CUSTOMER.name(), Role.ROLE_CUSTOMER.getAuthorities());
       userDetailsManager.createUser(savedUser);
       return "Thank you for signing up " + registerUser.getUsername();
    }

}