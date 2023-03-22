package me.potato.todoorderingstudy.controller.utils;

import me.potato.todoorderingstudy.controller.dto.CreateOrderingRequest;
import me.potato.todoorderingstudy.controller.dto.ErrorResponse;
import me.potato.todoorderingstudy.service.exceptions.CommonException;
import me.potato.todoorderingstudy.store.entity.Ordering;

public class EntityDtoUtils {

    public static Ordering toEntity(CreateOrderingRequest request) {
        Ordering ordering = new Ordering();
        ordering.setUserNo(request.getUserId());
        ordering.setTodos(request.getTodos());
        return ordering;
    }

    public static ErrorResponse toErrorDto(CommonException e) {
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

}
