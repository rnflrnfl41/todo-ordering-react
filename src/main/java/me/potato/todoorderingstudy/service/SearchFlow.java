package me.potato.todoorderingstudy.service;

import lombok.RequiredArgsConstructor;
import me.potato.todoorderingstudy.controller.dto.CreateTodoRequest;
import me.potato.todoorderingstudy.service.exceptions.OrderingNotExistException;
import me.potato.todoorderingstudy.service.exceptions.TodoNotExistException;
import me.potato.todoorderingstudy.store.entity.Ordering;
import me.potato.todoorderingstudy.store.entity.OrderingRepository;
import me.potato.todoorderingstudy.store.entity.Todo;
import me.potato.todoorderingstudy.store.entity.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchFlow {

    private final TodoRepository todoRepository;
    private final OrderingRepository orderingRepository;

    public Ordering createSearchFlow(CreateTodoRequest request) {
        Todo todo = new Todo();
        todo.setItem(request.getItem());
        todo.setUserNo(request.getUserNo());
        todo.setCreatedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        todo.setStatus("registered");
        todo.setCompletedTime("still progressing..");
        todoRepository.save(todo);

        Optional<Ordering> ordering = orderingRepository.findByUserNo(todo.getUserNo());
        System.out.println(ordering);

        Ordering newOrdering = new Ordering();
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo);
        newOrdering.setUserNo(todo.getUserNo());
        newOrdering.setTodos(todoList);

        return ordering.map(o -> {
            List<Todo> list = o.getTodos();
            list.add(todo);
            o.setTodos(list);
            return orderingRepository.save(o);
        }).orElseGet(() -> orderingRepository.save(newOrdering));

    }

    public Ordering completeSearchFlow(Long todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        todo.map( t -> {
            t.setStatus("completed");
            t.setCompletedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            return todoRepository.save(t);
        }).orElseThrow(() -> new TodoNotExistException("10004","todo not exist.."));

        Optional<Ordering> ordering = orderingRepository.findByUserNo(todo.orElseThrow().getUserNo());
        return ordering.map( o -> {
            List<Todo> todos = o.getTodos();
            for(int i=0; i < todos.toArray().length; i++) {
               Todo getTodo = todos.get(i);
                if(getTodo.getId() == todoId) {
                    getTodo.setStatus("completed");
                    getTodo.setCompletedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }
            return orderingRepository.save(o);
        }).orElseThrow(() -> new TodoNotExistException("10004","todo not exist.."));
    }

    public void delete(Long todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if(todo.isEmpty()) {
            throw new TodoNotExistException("10004","todo not exist..");
        }
        todoRepository.deleteById(todoId);
        Optional<Ordering> ordering = orderingRepository.findByUserNo(todo.orElseThrow().getUserNo());
        ordering.map(o -> {
            List<Todo> todos = o.getTodos();
            for(int i=0; i < todos.toArray().length; i++) {
                Todo getTodo = todos.get(i);
                if(getTodo.getId() == todoId) {
                    todos.remove(i);
                }
            }
            return orderingRepository.save(o);
        }).orElseThrow(() -> new TodoNotExistException("10004","todo not exist.."));
    }





}
