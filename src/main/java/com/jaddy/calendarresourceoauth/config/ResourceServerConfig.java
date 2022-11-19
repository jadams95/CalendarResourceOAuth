package com.jaddy.calendarresourceoauth.config;

import com.jaddy.calendarresourceoauth.service.userservices.CustomerDetailsService;
import com.jaddy.calendarresourceoauth.filters.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider).userDetailsService(customerDetailsService);
        return authenticationManagerBuilder.build();
    }
    @Bean
    public SecurityFilterChain resourceConfig(HttpSecurity http) throws Exception {
       http.headers().httpStrictTransportSecurity().disable().and().csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults())
               .authenticationProvider(authProvider)
               .authorizeRequests(x ->
                       x.antMatchers("/register").permitAll()
                               .antMatchers("/token").permitAll()
                               .antMatchers("/schedule").hasAuthority("SCOPE_manager:create")
                               .antMatchers("/schedulePlan").hasAuthority("SCOPE_manager:create")
                               .antMatchers("/schedulePlan").hasAuthority("SCOPE_manager:read")
                               .antMatchers("/schedulePlan").hasAuthority("SCOPE_manager:update")
                       .anyRequest().authenticated()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
               .httpBasic();
       return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.setAllowedOrigins(List.of("http://localhost:4200"));
        cors.setAllowedHeaders(List.of("*"));
        cors.setAllowedMethods(List.of("*"));
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

}
