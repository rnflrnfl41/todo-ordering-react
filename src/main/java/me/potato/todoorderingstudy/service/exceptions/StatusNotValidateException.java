package me.potato.todoorderingstudy.service.exceptions;

public class StatusNotValidateException extends CommonException{
    public StatusNotValidateException(String code, String message) {
        super(code, message);
    }
}
