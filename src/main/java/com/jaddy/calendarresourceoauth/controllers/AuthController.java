package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.dao.CustomerDao;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.service.authservices.ManagerTokenService;
import com.jaddy.calendarresourceoauth.service.authservices.TokenService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import javax.transaction.Transactional;

import java.util.Random;
import java.util.random.RandomGenerator;

import static java.util.Arrays.stream;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

//    private NoOpPasswordEncoder noOpPasswordEncoder;

    private final TokenService tokenService;

    private final ManagerTokenService managerTokenService;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    public AuthController(TokenService tokenService, ManagerTokenService managerTokenService) {
        this.tokenService = tokenService;
        this.managerTokenService = managerTokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<String> token(Authentication authentication) {

            LOG.debug("Token requred for user has details: '{}'", authentication.getDetails());
            LOG.debug("Token requested for user: '{}'", authentication.getName());
            LOG.debug("Token requested for user: '{}'", authentication.getCredentials());
            LOG.debug("Token requested for user: '{}'", authentication.getPrincipal());
            String token = tokenService.generateToken(authentication);
            LOG.debug("Token granted: {}", token);
            return new ResponseEntity<>(token, HttpStatus.OK);
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
    @Transactional
    public String registerUser(@RequestBody Customer registerUser){
       Customer savedUser =  new Customer(generateRandomId(), registerUser.getUsername(), "{noop}" + registerUser.getPassword(), Role.ROLE_CUSTOMER.name(), Role.ROLE_CUSTOMER.getAuthorities());
       customerDao.save(savedUser);
       userDetailsManager.createUser(savedUser);
       return registerUser.getUsername();
    }

    public Long generateRandomId(){
        Random random = new Random();
        return random.nextLong();
    }

}