package me.potato.todoorderingstudy.service.exceptions;

public class OrderingNotExistException extends CommonException{
    public OrderingNotExistException(String code, String message) {
        super(code, message);
    }
}
