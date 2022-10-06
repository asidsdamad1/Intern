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

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(Role.builder()
//                                    .name("ROLE_USER")
//                                    .build());
//            userService.saveRole(Role.builder()
//                                    .name("ROLE_ADMIN")
//                                    .build());
//
//            User user1 = User.builder()
//                    .username("admin")
//                    .password("1234")
//                    .build();
//            User user2 = User.builder()
//                    .username("user1")
//                    .password("1234")
//                    .build();
//            userService.saveUser(new UserRequestDto(user1));
//            userService.saveUser(new UserRequestDto(user2));
//        };
//    }
}
