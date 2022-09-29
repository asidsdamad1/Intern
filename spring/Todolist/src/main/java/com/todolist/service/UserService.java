package com.todolist.service;

import com.todolist.domain.Role;
import com.todolist.domain.User;
import com.todolist.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    Role saveRole(Role role);

    UserDto deleteById(Long userId);

    UserDto getCurrentUser();

    User getUserByUsername(String username);

    void addRoleToUser(String username, String roleName);

    List<UserDto> getUsers();
}
