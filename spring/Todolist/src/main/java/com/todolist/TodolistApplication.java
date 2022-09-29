package com.todolist;

import com.todolist.domain.Role;
import com.todolist.domain.User;
import com.todolist.dto.UserDto;
import com.todolist.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//            User user1 = new User("user1", "1234");
//            User user2 = new User("user2", "asdf");
//            User user3 = new User("user3", "4321");
//            userService.saveUser(new UserDto(user1));
//            userService.saveUser(new UserDto(user2));
//            userService.saveUser(new UserDto(user3));
//        };
//    }
}
