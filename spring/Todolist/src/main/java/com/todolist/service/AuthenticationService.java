package com.todolist.service;

import com.todolist.dto.request.UserRequestDto;
import com.todolist.security.response.AuthenticationResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    AuthenticationResponse createAuthenticationToken(UserRequestDto authenticationRequest);

    AuthenticationResponse refreshToken(HttpServletRequest request);
}
