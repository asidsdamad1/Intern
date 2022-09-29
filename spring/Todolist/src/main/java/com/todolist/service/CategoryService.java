package com.todolist.service;

import com.todolist.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveOrUpdate(CategoryDto dto, Long id);

    CategoryDto getById(Long id);

    List<CategoryDto> getAll();

    List<CategoryDto> searchByDto(CategoryDto dto);

    Boolean deleteById(Long id);
}
