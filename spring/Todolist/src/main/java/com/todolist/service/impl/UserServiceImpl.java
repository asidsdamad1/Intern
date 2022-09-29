package com.todolist.service.impl;

import com.todolist.domain.Role;
import com.todolist.domain.User;
import com.todolist.dto.UserDto;
import com.todolist.repository.RoleRepository;
import com.todolist.repository.UserRepository;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserDto saveUser(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();

        Role role = roleRepo.findByName("ROLE_USER");

        Set<Role> roles = new HashSet();
        roles.add(role);

        user.setRole(roles);
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user = userRepo.save(user);

        if (user != null) {
            return new UserDto(user);
        }

        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public UserDto deleteById(Long userId) {
        return null;
    }

    @Override
    public UserDto getCurrentUser() {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRole().add(role);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> todos = userRepo.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User todo : todos) {
            dtoList.add(new UserDto(todo));
        }
        return dtoList;
    }


}
