package com.todolist.rest;


import com.todolist.security.jwt.JWTUtils;
import com.todolist.security.model.AuthenticationRequest;
import com.todolist.security.model.AuthenticationResponse;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api")
public class RestAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String jwt = jwtUtils.generateToken(userDetails);
        int expired = jwtUtils.getJwtExpirationInMs();
        return ResponseEntity.ok(
                new AuthenticationResponse(jwt, expired, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    private ResponseEntity<?> refreshToken(HttpServletRequest request) throws IOException {
        String tokenPrefix =  jwtUtils.getTokenPrefix();
        DefaultClaims claims = (DefaultClaims) jwtUtils.extractAllClaims(request.getHeader(AUTHORIZATION).substring(tokenPrefix.length()) );

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtils.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        int expired = jwtUtils.getRefreshExpirationDateInMs();
        return ResponseEntity.ok(new AuthenticationResponse(token, expired));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }
}
