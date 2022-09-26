package com.todolist.service.impl;

import com.todolist.dto.UserDto;
import com.todolist.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto saveOrUpdate(UserDto dto, Long id) {
        return null;
    }

    @Override
    public UserDto getById(Long id) {
        return null;
    }

    @Override
    public UserDto searchByDto(UserDto dto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }
}
