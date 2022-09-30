package com.todolist.service;

import com.todolist.dto.CategoryDto;
import com.todolist.dto.UserDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveOrUpdate(CategoryDto dto, Long id);

    CategoryDto getById(Long id);

    List<CategoryDto> getByUser(UserDto userDto);

    List<CategoryDto> searchByDto(CategoryDto dto);

    Boolean deleteById(Long id);
}
