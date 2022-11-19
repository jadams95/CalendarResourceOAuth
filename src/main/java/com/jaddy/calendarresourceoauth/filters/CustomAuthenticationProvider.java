package com.jaddy.calendarresourceoauth.filters;

import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.service.authservices.TokenService;
import com.jaddy.calendarresourceoauth.service.userservices.CustomerDetailsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userEntity = customerDetailsService.loadUserByUsername(authentication.getName());

        if (userEntity != null) {
            // use the credentials

            return new UsernamePasswordAuthenticationToken(username, password, userEntity.getAuthorities());
        }
//        if (userEntity != null && userEntity.getAuthorities().contains(Role.ROLE_MANAGER.getAuthorities())) {
//            // use the credentials
//            return new UsernamePasswordAuthenticationToken(username, password, userEntity.getAuthorities());
//        }

        else {
            throw new BadCredentialsException("Incorrect user Credentials!");
        }
//        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
