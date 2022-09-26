package com.todolist.service;

import com.todolist.dto.UserDto;
import org.springframework.stereotype.Service;

public interface UserService {
    UserDto saveOrUpdate(UserDto dto, Long id);

    UserDto getById(Long id);

    UserDto searchByDto(UserDto dto);

    Boolean deleteById(Long id);
}
