package com.todolist.service;

import com.todolist.dto.request.TodoRequestDto;
import com.todolist.dto.request.UserRequestDto;
import com.todolist.dto.response.TodoResponseDto;
import com.todolist.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;

public interface TodoService {
    TodoResponseDto save(TodoRequestDto todoRequestDto);

    TodoResponseDto update(TodoRequestDto todoRequestDto, Long id);

    TodoResponseDto getById(Long id);

    Page<TodoResponseDto> getAll(int page, int size, String[] sorts, UserResponseDto userResponseDto);

    Boolean deleteById(Long id);
}
