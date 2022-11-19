package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.dao.CustomerDao;
import com.jaddy.calendarresourceoauth.dao.UsersDao;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.ds.users.Users;
import com.jaddy.calendarresourceoauth.model.dtos.CustomerDTO;
//import com.jaddy.calendarresourceoauth.model.dtos.CustomerPrincipal;
import com.jaddy.calendarresourceoauth.service.authservices.TokenService;
import com.jaddy.calendarresourceoauth.service.userservices.CustomerDetailsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.transaction.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.util.Arrays.stream;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);


    private final TokenService tokenService;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private CustomerDetailsService customerDetailsService;
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping(value = "/token", produces = {"text/plain"})
    public ResponseEntity<String> loginToken(Authentication authentication) {
        if (authentication.getAuthorities().containsAll(stream(Role.ROLE_CUSTOMER.getAuthorities())
                .map(SimpleGrantedAuthority::new).toList())) {
            LOG.info("Token requred for user has details: '{}'", authentication.getDetails());
            LOG.info("Token requested for user: '{}'", authentication.getName());
            LOG.info("Token requested for user: '{}'", authentication.getCredentials());
            LOG.info("Token requested for user: '{}'", authentication.getPrincipal());
            String token = tokenService.generateToken(authentication);
            LOG.info("Token granted: {}", token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        if (authentication.getAuthorities()
                .containsAll(stream(Role.ROLE_MANAGER.getAuthorities())
                        .map(SimpleGrantedAuthority::new).toList())) {
            LOG.info("Token requred for user has details: '{}'", authentication.getDetails());
            LOG.info("Token requested for user: '{}'", authentication.getName());
            LOG.info("Token requested for user: '{}'", authentication.getCredentials());
            LOG.info("Token requested for user: '{}'", authentication.getPrincipal());
            String token = tokenService.generateMngrToken(authentication);
            LOG.info("Token granted: {}", token);
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
    public String registerUser(@RequestBody Users customer) throws NoSuchAlgorithmException {
       Users savedUser =  new Users(generateRandomId(), customer.getUsername(), "{noop}" + customer.getPassword(), Role.ROLE_CUSTOMER.name(), Role.ROLE_CUSTOMER.getAuthorities());
//       usersDao.save(new Users(generateRandomId(), customer.getUsername(), "{noop}" + customer.getPassword(), Role.ROLE_CUSTOMER.name(), Role.ROLE_CUSTOMER.getAuthorities()));
       return customerDetailsService.saveCustomer(savedUser).getUsername();
    }

    public Long generateRandomId() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        Long userId = random.nextLong();
        if(userId.longValue() < 0) return userId * -1;
        return userId;
    }

}