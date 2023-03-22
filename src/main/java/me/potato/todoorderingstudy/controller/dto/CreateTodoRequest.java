package me.potato.todoorderingstudy.controller.dto;

import lombok.Data;

@Data
public class CreateTodoRequest {

    private String item;
    private Long userNo;
}
