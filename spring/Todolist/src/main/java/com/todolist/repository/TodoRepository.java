package com.todolist.repository;

import com.todolist.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select e from Todo e where e.id = ?1")
    Todo getById(Long id);

    Optional<Todo> findById(Long id);

    @Query("select e from Todo e where e.category.id = ?1")
    List<Todo> getByCategoryId(Long id);

    @Query("select e from Todo e where e.category.user.id=?1")
    Page<Todo> findAll(Long id, Pageable pageable);
}
