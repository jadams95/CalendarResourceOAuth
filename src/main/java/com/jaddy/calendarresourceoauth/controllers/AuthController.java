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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.security.auth.Subject;
import javax.transaction.Transactional;

import java.util.Collections;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

//    private NoOpPasswordEncoder noOpPasswordEncoder;

    private final TokenService tokenService;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PreAuthorize("hasAnyAuthority('customer:read', 'customer:update', 'customer:create', 'manager:read', 'manager:update', 'manager:create', 'manager:delete')")
    @PostMapping(value = "/token", produces = {"text/plain"})
    public ResponseEntity<String> loginToken(Authentication authentication) {
        if(authentication.getAuthorities().containsAll(stream(Role.ROLE_CUSTOMER.getAuthorities())
                .map(SimpleGrantedAuthority::new).toList())) {
            LOG.debug("THE LIST OF AUTHORITY ------->" + authentication.getAuthorities().toString());
            LOG.debug("THE AUTHENTICATION DETAILS ------------>" + authentication.getDetails().toString());
            LOG.debug("THE USER PRINCIPAL -------->" + authentication.getPrincipal());
            LOG.debug("Token requred for user has details: '{}'", authentication.getDetails());
            LOG.debug("Token requested for user: '{}'", authentication.getName());
            LOG.debug("Token requested for user: '{}'", authentication.getCredentials());
            LOG.debug("Token requested for user: '{}'", authentication.getPrincipal());
            String token = tokenService.generateToken(authentication);
            LOG.debug("Token granted: {}", token);
            return new ResponseEntity<>(token, HttpStatus.OK);
            // Removed if statement
            }
        if(authentication.getAuthorities()
                .containsAll(stream(Role.ROLE_MANAGER.getAuthorities())
                        .map(SimpleGrantedAuthority::new).toList())){
            LOG.info("Token requred for user has details: '{}'", authentication.getPrincipal());
            LOG.debug("Token requested for user: '{}'", authentication.getName());
            String token = tokenService.generateMngrToken(authentication);
            LOG.debug("Token granted: {}", token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

// -------- Can Delete in a little refactoring to be in one method
//    @PreAuthorize("hasAuthority('manager:create')")
//    @PostMapping(value = "/token", produces = {"text/plain"}, headers = {"x-rb-title=manager"})
//    public ResponseEntity<String> tokenMnger(Authentication authentication){
//            LOG.info("Token requred for user has details: '{}'", authentication.getPrincipal());
//            LOG.debug("Token requested for user: '{}'", authentication.getName());
//            String token = tokenService.generateMngrToken(authentication);
//            LOG.debug("Token granted: {}", token);
//            return new ResponseEntity<>(token, HttpStatus.OK);
//    }

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