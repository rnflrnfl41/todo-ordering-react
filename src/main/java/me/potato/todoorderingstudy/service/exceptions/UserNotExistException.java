package me.potato.todoorderingstudy.service.exceptions;

public class UserNotExistException extends CommonException{
    public UserNotExistException(String code, String message) {
        super(code, message);
    }
}
