package com.todolist;

import com.todolist.rest.RestCategoryController;
import com.todolist.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodolistApplicationTests {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RestCategoryController categoryController;

    @Test
    void contextLoads() throws Exception {
        assertThat(categoryService).isNotNull();
        assertThat(categoryController).isNotNull();
    }

}
