package com.todolist.dto;

import com.todolist.domain.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDto extends BaseDto {
    private String title;
    private String notes;
    private boolean done;
    ;
    private CategoryDto category;
    private TodoDto parent;
    private List<TodoDto> todoItems;

    public TodoDto() {
    }

    public TodoDto(Todo entity) {
        this(entity, true);
    }

    public TodoDto(Todo entity, boolean simple) {
        if (entity != null) {
            this.id = entity.getId();
            this.createDate = entity.getCreatedAt();
            this.title = entity.getTitle();
            this.notes = entity.getNotes();
            this.done = entity.isDone();
            if (entity.getCategory() != null)
                this.category = new CategoryDto(entity.getCategory(), false);

            if (entity.getParent() != null) {
                Todo parent = new Todo();
                parent.setId(entity.getParent().getId());
                parent.setCreatedAt(entity.getParent().getCreatedAt());
                parent.setTitle(entity.getParent().getTitle());
                parent.setNotes(entity.getParent().getNotes());
                parent.setDone(entity.getParent().isDone());
                this.parent = new TodoDto(parent, true);
            }

            todoItems = new ArrayList<>();
            if (entity.getTodoItems() != null && entity.getTodoItems().size() > 0) {
                for (Todo todo : entity.getTodoItems()) {
                    TodoDto todoDto =new TodoDto();
                    todoDto.setId(todo.getId());
                    todoDto.setCreateDate(todo.getCreatedAt());
                    todoDto.setTitle(todo.getTitle());
                    todoDto.setNotes(todo.getNotes());
                    todoDto.setDone(todo.isDone());
                    this.todoItems.add(todoDto);
                }
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public TodoDto getParent() {
        return parent;
    }

    public void setParent(TodoDto parent) {
        this.parent = parent;
    }

    public List<TodoDto> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoDto> todoItems) {
        this.todoItems = todoItems;
    }
}
