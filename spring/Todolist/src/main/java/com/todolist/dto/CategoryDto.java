package com.todolist.dto;

import com.todolist.model.Category;
import com.todolist.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto extends BaseDto{
    private String name;
    private String description;
    private UserDto user;
    private List<TodoDto> todos;

    public CategoryDto() {
    }

    public CategoryDto(Category entity) {
        this(entity, true);
    }

    public CategoryDto(Category entity,  boolean simple) {
        if(entity != null)  {
            this.id  = entity.getId();
            this.createDate = entity.getCreatedAt();
            this.name = entity.getName();
            this.description  = entity.getDescription();
            if(entity.getUser() != null)
                this.user = new UserDto(entity.getUser());

            if(simple) {
                if(entity.getTodos() != null && entity.getTodos().size() > 0) {
                    this.todos = new ArrayList<>();
                    for (Todo todo : entity.getTodos())
                        this.todos.add(new TodoDto(todo));
                }
            }

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<TodoDto> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoDto> todos) {
        this.todos = todos;
    }
}
