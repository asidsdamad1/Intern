package com.todolist.service;

import com.todolist.dto.TodoDto;
import com.todolist.dto.UserDto;

import java.util.List;

public interface TodoService {
    TodoDto saveOrUpdate(TodoDto dto, Long id);

    TodoDto getById(Long id);

    List<TodoDto> getAll(UserDto  userDto);

    List<TodoDto> searchByDto(TodoDto dto);

    Boolean deleteById(Long id);
}
