package com.todolist.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todo")
public class Todo  extends BaseObject {
    @Column(name = "title")
    private String title;

    @Column(name = "notes")
    private String notes;

    @Column(name = "content")
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "parent")
    private List<Todo> todoItems;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Todo parent;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Todo> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<Todo> todoItems) {
        this.todoItems = todoItems;
    }

    public Todo getParent() {
        return parent;
    }

    public void setParent(Todo parent) {
        this.parent = parent;
    }
}
