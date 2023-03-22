package me.potato.todoorderingstudy.controller;

import lombok.RequiredArgsConstructor;
import me.potato.todoorderingstudy.controller.dto.CreateTodoRequest;
import me.potato.todoorderingstudy.service.TodoService;
import me.potato.todoorderingstudy.store.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("todos")
public class TodoController {

    private final TodoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody CreateTodoRequest request) {
        return service.createTodo(request);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo completeTodo(@PathVariable Long id) {
        return service.completeTodo(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @GetMapping("{status}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Todo> getAllByStatus(@PathVariable String status) {
        return service.getTodosByStatus(status);
    }

    @GetMapping("{userNo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Todo> getAllByUserNo(@PathVariable Long userNo) {
        return service.getTodosByUserNo(userNo);
    }

}
