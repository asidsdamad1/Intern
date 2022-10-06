package com.todolist.repository;

import com.todolist.domain.SubTodo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubTodoRepository extends JpaRepository<SubTodo, Long> {
    @Query("select e from SubTodo e where e.parent.id = ?1")
    List<SubTodo> getByParentId(Long parentId);

    @Query("select e from SubTodo e where e.parent.category.user.id = ?1")
    Page<SubTodo> findAll(Long id, Pageable pageable);
}
