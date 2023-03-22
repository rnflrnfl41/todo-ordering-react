package me.potato.todoorderingstudy.service.exceptions;

public class TodoNotExistException extends CommonException{
    public TodoNotExistException(String code, String message) {
        super(code, message);
    }
}
