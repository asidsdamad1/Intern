package com.todolist.rest;


import com.todolist.dto.request.UserRequestDto;
import com.todolist.security.response.AuthenticationResponse;
import com.todolist.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestAuthenticationController {
    private final AuthenticationService service;

    @PostMapping(value = "/signin")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody UserRequestDto authenticationRequest) {
        return ResponseEntity.ok(service.createAuthenticationToken(authenticationRequest));
    }

    @GetMapping(value = "/token/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(service.refreshToken(request));
    }
}
