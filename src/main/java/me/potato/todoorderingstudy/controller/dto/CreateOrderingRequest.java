package me.potato.todoorderingstudy.controller.dto;

import lombok.Data;
import me.potato.todoorderingstudy.store.entity.Todo;

import java.util.List;

@Data
public class CreateOrderingRequest {

    private Long userId;
    private List<Todo> todos;

}
