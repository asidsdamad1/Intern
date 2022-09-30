package com.todolist.dto;

import com.todolist.domain.Category;
import com.todolist.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends BaseDto{
    private String username;
    private String password;
    private List<CategoryDto> categories;

    public UserDto(User entity) {
        this(entity, true);
    }

    public UserDto(User entity, boolean simple) {
        if(entity != null) {
            this.id = entity.getId();
            this.createDate = entity.getCreatedAt();
            this.username = entity.getUsername();
            this.password = entity.getPassword();

            if(simple) {
                if(entity.getCategories() != null && entity.getCategories().size() > 0 ) {
                    this.categories = new ArrayList<>();
                    for(Category category : entity.getCategories()) {
                        this.categories.add(new CategoryDto(category));
                    }
                }
            }

        }
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
