package com.todolist.service;

import com.todolist.dto.request.SubTodoRequestDto;
import com.todolist.dto.request.UserRequestDto;
import com.todolist.dto.response.SubTodoResponseDto;
import com.todolist.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;

public interface SubTodoService {
    SubTodoResponseDto save(SubTodoRequestDto todoRequestDto);

    SubTodoResponseDto update(SubTodoRequestDto todoRequestDto, Long id);

    SubTodoResponseDto getById(Long id);

    Page<SubTodoResponseDto> getAll(int page, int size, String[] sorts, UserResponseDto userResponseDto);

    Boolean deleteById(Long id);
}
