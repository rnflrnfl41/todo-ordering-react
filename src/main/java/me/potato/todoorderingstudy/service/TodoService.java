package me.potato.todoorderingstudy.service;

import lombok.RequiredArgsConstructor;
import me.potato.todoorderingstudy.controller.dto.CreateTodoRequest;
import me.potato.todoorderingstudy.service.exceptions.StatusNotValidateException;
import me.potato.todoorderingstudy.service.exceptions.TodoNotExistException;
import me.potato.todoorderingstudy.service.exceptions.UserNotExistException;
import me.potato.todoorderingstudy.store.entity.Todo;
import me.potato.todoorderingstudy.store.entity.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public Todo createTodo(CreateTodoRequest request) {
        Todo todo = new Todo();
        todo.setItem(request.getItem());
        todo.setUserNo(request.getUserNo());
        todo.setCreatedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        todo.setStatus("registered");
        todo.setCompletedTime("still progressing..");

        return repository.save(todo);
    }

    public Todo completeTodo(Long id) {

        Optional<Todo> existTodo = repository.findById(id);
        return existTodo.map(t ->  {
            t.setStatus("completed");
            t.setCompletedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            return repository.save(t);
        }).orElseThrow(() -> new TodoNotExistException("1000","Todo Not Exist.."));

    }
    public void deleteTodo(Long id) {
        Optional<Todo> existTodo = repository.findById(id);
        existTodo.orElseThrow(() -> new TodoNotExistException("1000","Todo Not Exist.."));
        repository.deleteById(id);
    }

    public List<Todo> getAllTodos() {
        if(repository.findAll().isEmpty()) {
            throw new TodoNotExistException("1000","Todo Not Exist..");
        }

        return repository.findAll();
    }

    public List<Todo> getTodosByStatus(String status) {
        if(status != "registered" || status != "completed") {
            throw new StatusNotValidateException("1001","Status is not validate..");
        }
        return repository.findByStatus(status);
    }

    public List<Todo> getTodosByUserNo(Long userNo) {
        return repository.findByUserNo(userNo).orElseThrow(() -> new UserNotExistException("1002","User Not Exist.."));
    }

}
