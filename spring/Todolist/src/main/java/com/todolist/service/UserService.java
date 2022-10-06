package com.todolist.service;

import com.todolist.domain.Role;
import com.todolist.dto.request.UserRequestDto;
import com.todolist.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UserRequestDto userRequestDto, Long id);

    Role saveRole(Role role);

    UserResponseDto getCurrentUser();

    UserDetails getUserByUsername(String username);

    void addRoleToUser(String username, String roleName);

    Page<UserResponseDto> getAll(int page, int size, String[] sorts);

    Boolean deleteUser(Long id);
}
