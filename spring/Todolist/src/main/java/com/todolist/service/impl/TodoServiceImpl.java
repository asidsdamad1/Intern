package com.todolist.service.impl;

import com.todolist.dto.TodoDto;
import com.todolist.exception.ApiResquestException;
import com.todolist.domain.Category;
import com.todolist.domain.Todo;
import com.todolist.repository.CategoryRepository;
import com.todolist.repository.TodoRepository;
import com.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public TodoDto saveOrUpdate(TodoDto dto, Long id) {
        if(dto != null) {
            Todo entity = null;

            if(id != null) {
                entity = todoRepository.getReferenceById(id);
            } else {
                entity = new Todo();
            }

            entity.setTitle(dto.getTitle());
            entity.setNotes(dto.getNotes());
            entity.setDone(dto.isDone());

            if(dto.getCategory() != null  && dto.getCategory().getId() != null) {
                Category category = categoryRepository.getReferenceById(dto.getCategory().getId());
                if(category != null)
                    entity.setCategory(category);
            } else {
                entity.setCategory(null);
            }

            if(dto.getParent() != null && dto.getParent().getId() != null) {
                Todo parent = todoRepository.getReferenceById(dto.getParent().getId());
                if(parent != null)
                    entity.setParent(parent);
            } else {
                entity.setParent(null);
            }

            List<Todo> todoItems = new ArrayList<>();
            if(dto.getTodoItems() != null && dto.getTodoItems().size() > 0) {
                for(TodoDto itemDto : dto.getTodoItems()) {
                    Todo item = null;
                    if(itemDto.getId() != null) {
                        item = todoRepository.getReferenceById(itemDto.getId());
                    } else {
                        item = new Todo();
                    }

                    todoItems.add(item);
                }
            }

            if(entity.getTodoItems() == null) {
                entity.setTodoItems(todoItems);
            } else {
                entity.getTodoItems().clear();
                entity.getTodoItems().addAll(todoItems);
            }

            entity = todoRepository.save(entity);

            if(entity != null) {
                return new TodoDto(entity);
            }
        }
        return null;
    }

    @Override
    public TodoDto getById(Long id) {
        if(id != null) {
            Todo entity = todoRepository.getById(id);
            if(entity != null)
                return new TodoDto(entity);
        }
        throw new ApiResquestException("Oops cannot get all todos with customer exception");
    }

    @Override
    public List<TodoDto> searchByDto(TodoDto dto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        if(id != null) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<TodoDto> getAll() {
        List<Todo> todos = todoRepository.getAll();
        List<TodoDto> dtoList = new ArrayList<>();
        for(Todo todo : todos) {
            System.out.println(todo);
            System.out.println(todo.getTitle() + " has " + todo.getTodoItems().size() + " todoItems.");
            dtoList.add(new TodoDto(todo));

        }
        return dtoList;

    }
}
