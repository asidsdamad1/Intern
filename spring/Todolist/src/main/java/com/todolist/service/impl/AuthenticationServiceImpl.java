package com.todolist.service.impl;

import com.todolist.dto.request.UserRequestDto;
import com.todolist.exception.request.UnauthenticatedException;
import com.todolist.security.jwt.JWTUtils;
import com.todolist.security.response.AuthenticationResponse;
import com.todolist.service.AuthenticationService;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    @Override
    public AuthenticationResponse createAuthenticationToken(UserRequestDto authenticationRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

        } catch (BadCredentialsException e) {
            throw new UnauthenticatedException("Incorrect username or password");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String jwt = jwtUtils.generateToken(userDetails);
        int expired = jwtUtils.getJwtExpirationInMs();
        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword(), userDetails.getAuthorities());
        return new AuthenticationResponse(jwt, expired, userDetails.getUsername(), userDetails.getAuthorities());
    }

    @Override
    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        String tokenPrefix = jwtUtils.getTokenPrefix();
        DefaultClaims claims = (DefaultClaims) jwtUtils.extractAllClaims(request.getHeader(AUTHORIZATION).substring(tokenPrefix.length()));

        Map<String, Object> expectedMap = new HashMap<>(claims);
        String token = jwtUtils.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        int expired = jwtUtils.getRefreshExpirationDateInMs();
        return new AuthenticationResponse(token, expired);
    }
}
