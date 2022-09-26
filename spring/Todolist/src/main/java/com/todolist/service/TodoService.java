package com.todolist.service;

import com.todolist.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {
    TodoDto saveOrUpdate(TodoDto dto, Long id);

    TodoDto getById(Long id);

    TodoDto searchByDto(TodoDto dto);

    Boolean deleteById(Long id);
}
