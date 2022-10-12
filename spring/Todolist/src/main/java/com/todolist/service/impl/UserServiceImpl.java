package com.todolist.service.impl;

import com.todolist.domain.Category;
import com.todolist.domain.Role;
import com.todolist.domain.User;
import com.todolist.dto.request.UserRequestDto;
import com.todolist.dto.response.UserResponseDto;
import com.todolist.exception.request.BadRequestException;
import com.todolist.repository.CategoryRepository;
import com.todolist.repository.RoleRepository;
import com.todolist.repository.UserRepository;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    public UserResponseDto getById(Long id) {
        return userRepo.findById(id)
                .map(UserResponseDto::of)
                .orElseThrow(() -> {
                    throw new BadRequestException("UserId can not be found");
                });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role.getName()))
        );

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public com.todolist.dto.response.UserResponseDto saveUser(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Role role = roleRepo.findByName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(roles)
                .build();

        return com.todolist.dto.response.UserResponseDto.toDto(userRepo.save(user));
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto dto, Long id) {
        if (id == null) {
            throw new BadRequestException("User Id is null");
        }
        UserResponseDto responseDto = getById(id);

        if (dto != null) {
            List<Category> categories = categoryRepository.getByUserId(id);
            User user = User.builder()
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .categories(categories)
                    .build();
            user.setId(responseDto.getId());

            return UserResponseDto.toDto(userRepo.save(user));
        }
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (id == null) {
            throw new BadRequestException("User Id is null");
        }
        // throw EntityNotfoundNotFound
        UserResponseDto responseDto = getById(id);
        if (responseDto != null) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserResponseDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            UserDetails userDetail = (UserDetails) principal;
            userName = userDetail.getUsername();
        } else {
            userName = principal.toString();
        }

        if (userName != null) {
            return UserResponseDto.toDto(userRepo.getByUsername(userName));
        }

        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserResponseDto userDto = (UserResponseDto) getUserByUsername(username);
        User user = UserResponseDto.toEntity(userDto);

        Role role = roleRepo.findByName(roleName);
        user.getRole().add(role);
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        return userRepo.getByUsername(username);
    }

    @Override
    public Page<UserResponseDto> getAll(int page, int size, String[] sorts) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sorts[0].contains(",")) {
            //sort more than 2 fields, sortOrder="field, direction"
            for (String sortOrder : sorts) {
                String[] sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.valueOf(sort[1]), sort[0]));
            }
        } else {
            orders.add(new Sort.Order(Sort.Direction.valueOf(sorts[1]), sorts[0]));
        }
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<User> pageUsers = userRepo.findAll(pagingSort);

        List<UserResponseDto> userResponse = new ArrayList<>();
        for (User user : pageUsers.getContent()) {
            userResponse.add(UserResponseDto.toDto(user));
        }

        return new PageImpl<>(userResponse);
    }


}
