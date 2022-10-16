package com.jaddy.calendarresourceoauth.service.authservices;

import com.jaddy.calendarresourceoauth.constants.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.token.Token;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class ManagerTokenService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public ManagerTokenService(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public String generateMngrToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("role", Role.ROLE_MANAGER.name())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String decodeToken(Jwt jwt){
        return this.decoder.decode(JwtDecoders.fromIssuerLocation(jwt.getIssuer().toString()).decode(jwt.getTokenValue()).getClaimAsString("scope")).toString();
    }

}