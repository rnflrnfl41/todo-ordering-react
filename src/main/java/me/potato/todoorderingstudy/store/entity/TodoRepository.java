package me.potato.todoorderingstudy.store.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<Todo> findByStatus(String status);

    Optional<List<Todo>> findByUserNo(Long userNo);
}
