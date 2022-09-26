package com.todolist.service.impl;

import com.todolist.dto.CategoryDto;
import com.todolist.dto.TodoDto;
import com.todolist.model.Category;
import com.todolist.model.Todo;
import com.todolist.model.User;
import com.todolist.repository.CategoryRepository;
import com.todolist.repository.TodoRepository;
import com.todolist.repository.UserRepository;
import com.todolist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;

    @Override
    public CategoryDto saveOrUpdate(CategoryDto dto, Long id) {
        if(dto != null) {
            Category entity = null;

            if(id != null)
                entity = categoryRepository.getReferenceById(id);
            else
                entity = new Category();

            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());

            if(dto.getUser() != null && dto.getUser().getId() > 0) {
                User user = userRepository.getReferenceById(entity.getUser().getId());
                if(user != null)
                    entity.setUser(user);
            } else {
                entity.setUser(null);
            }

            List<Todo> todos = new ArrayList<>();
            if(dto.getTodos() != null && dto.getTodos().size() > 0) {
                for(TodoDto todoDto : dto.getTodos()) {
                    Todo item = null;
                    if(todoDto.getId() != null) {
                        item = todoRepository.getReferenceById(todoDto.getId());
                    } else {
                        item = new Todo();
                    }

                    Todo parent = null;
                    if(todoDto.getParent() != null && todoDto.getParent().getId() != null) {
                        parent = todoRepository.getReferenceById(todoDto.getParent().getId());
                    }

                    item.setParent(parent);
                    todos.add(item);
                }
            }

            if(entity.getTodos() == null) {
                entity.setTodos(todos);
            } else {
                entity.getTodos().clear();
                entity.getTodos().addAll(todos);
            }

            entity = categoryRepository.save(entity);

            if(entity != null) {
                return new CategoryDto(entity);
            }
        }
        return null;
    }

    @Override
    public CategoryDto getById(Long id) {
        if(id != null) {
            Category entity = categoryRepository.getReferenceById(id);
            return new CategoryDto(entity);
        }
        return null;
    }

    @Override
    public List<CategoryDto> searchByDto(CategoryDto dto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        if(id != null) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
